package com.hcq.utils;

import java.io.File;
import java.io.FileNotFoundException;  
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;  
import org.apache.lucene.document.Field;  
import org.apache.lucene.document.TextField;  
import org.apache.lucene.document.Field.Store;  
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.DocsEnum;
import org.apache.lucene.index.IndexReader;  
import org.apache.lucene.index.IndexWriter;  
import org.apache.lucene.index.IndexWriterConfig;  
import org.apache.lucene.index.Term;
import org.apache.lucene.index.Terms;
import org.apache.lucene.index.TermsEnum;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.index.MultiFields;
import org.apache.lucene.queryparser.classic.ParseException;  
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.DocIdSetIterator;
import org.apache.lucene.search.IndexSearcher;  
import org.apache.lucene.search.Query;  
import org.apache.lucene.search.ScoreDoc;  
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.highlight.Fragmenter;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.search.highlight.SimpleSpanFragmenter;
import org.apache.lucene.store.Directory;  
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.BytesRef;
import org.apache.lucene.util.Version;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.chenlb.mmseg4j.Word;
import com.chenlb.mmseg4j.analysis.MMSegAnalyzer;
import com.hcq.bean.Message;
import com.hcq.bean.Users;
import com.hcq.biz.MessageBiz;
import com.hcq.biz.impl.MessageBizImpl;
import com.hcq.web.model.HotWord;

public class LuceneUtil  
{  
    private static Directory directory;  
    private IndexWriter writer;  
    private IndexReader reader;  
    
    

	static  
    {  
        try  
        {  
            directory = FSDirectory.open(new File("D://index"));  
        } catch (IOException e)  
        {  
            e.printStackTrace();  
        }  
    }  
  
    /** 
     * ??Directory?????????? ?????? 
     *  
     * @return 
     */  
    public static Directory getDirectory()  
    {  
        return directory;  
    }  
  
    /** 
     * ???IndexWriter???? 
     * @return 
     */  
    public IndexWriter getWriter(OpenMode createOrAppend)  
    {  
        if (writer != null)  
            return writer;  
  
        Analyzer analyzer = new MMSegAnalyzer();  
        IndexWriterConfig conf = new IndexWriterConfig(Version.LUCENE_4_10_4, analyzer);  
        if (createOrAppend == null)  
            // ??????????????  
            conf.setOpenMode(OpenMode.CREATE);  
        else  
            conf.setOpenMode(createOrAppend);  
  
        try  
        {  
            writer = new IndexWriter(directory, conf);  
            return writer;  
        } catch (IOException e)  
        {  
            e.printStackTrace();  
        }  
        return null;  
    }  
  
    public IndexReader getIndexReader()  
    {  
        try  
        {  
            DirectoryReader newReader = null;  
            // ?ж?reader?????? ?????????????μ?reader  
            if (reader == null)  
                reader = DirectoryReader.open(directory);  
            else  
                // ??????? ????????????????? ??????????????′???reader  
                newReader = DirectoryReader.openIfChanged((DirectoryReader) reader);  
            if (newReader != null)  
                reader = newReader;  
            return reader;  
        } catch (IOException e)  
        {  
            e.printStackTrace();  
        }  
        return null;  
  
    }  
  
    /** 
     * ???IndexSearcher???? 
     *  
     * @return 
     */  
    public IndexSearcher getIndexSearcher()  
    {  
        return new IndexSearcher(getIndexReader());  
    }  
  
  
    public void index() throws Exception  
    {  
        Document document = null;  
        writer = getWriter(OpenMode.CREATE);  
        ApplicationContext context=new ClassPathXmlApplicationContext("beans_core.xml");
		MessageBiz messageBiz=(MessageBiz) context.getBean("messageBizImpl");
        //数据库查询明星用户及近日微博
		List<Message>list = messageBiz.selectAllMessage();

        for (Message me:list)  
        {  
            document = new Document();  
            try  
            {  
            	String mcontent=me.getMcontent();
            	mcontent=mcontent.replaceAll("\\&[a-zA-Z]{1,10};","").replaceAll("<[^>]*>", "");
                mcontent=mcontent.replaceAll("[(/>)<]", "");
            	Field field = new TextField("Mcontent",mcontent,Store.YES);  
                document.add(field); 
                Field field1 = new TextField("Mid",String.valueOf(me.getMid()),Store.YES);  
                document.add(field1);  
                Field field2 = new TextField("Mdatetime",me.getMdatetime(),Store.YES);  
                document.add(field2); 
                Field field3 = new TextField("Uid",String.valueOf(me.getUser().getUid()),Store.YES);  
                document.add(field3); 
                Field field4 = new TextField("Ualais",String.valueOf(me.getUser().getUalais()),Store.YES);  
                document.add(field4); 
                Field field5 = new TextField("Uimage",String.valueOf(me.getUser().getUimage()),Store.YES);  
                document.add(field5); 
                if (writer.getConfig().getOpenMode() == OpenMode.CREATE)  
                {  
                    System.out.println("adding " + me);  
                    writer.addDocument(document);  
                } else  
                {  
                    System.out.println("updating " + me);  
                    writer.updateDocument(new Term("path", me.toString()), document);  
                }  
            } catch (FileNotFoundException e)  
            {  
                e.printStackTrace();  
            } catch (IOException e)  
            {  
                e.printStackTrace();  
            }  
        }  
        try  
        {  
            if (writer != null)  
                writer.close();  
        } catch (IOException e)  
        {  
            e.printStackTrace();  
        }  
    }  
  
    public List<Message> search(String queryStr, int num) throws InvalidTokenOffsetsException  
    {  
    	Analyzer analyzer = new MMSegAnalyzer();  
    	
        QueryParser parser = new QueryParser("Mcontent", analyzer);  
        IndexSearcher searcher = getIndexSearcher();  
        List<Message>list=new ArrayList<Message>();
        try  
        {  
            Query query = parser.parse(queryStr);  
            TopDocs docs = searcher.search(query, num);  
           
            System.out.println("????????????:" + docs.totalHits + "??");
            
            /**?????????????????*/ 
            
            SimpleHTMLFormatter formatter = new SimpleHTMLFormatter("<span style='color:red;'>", "</span>");  
            /**????QueryScorer    ?????н?????????????*/
            
            QueryScorer scorer=new QueryScorer(query);  
            
            /**????Fragmenter ?????????????????????*/
            Fragmenter fragmenter = new SimpleSpanFragmenter(scorer);  
            //??????????
            Highlighter highlight=new Highlighter(formatter,scorer);  
            highlight.setTextFragmenter(fragmenter);  
          
            
            Set<String> fieldSet = new HashSet<String>();  
            fieldSet.add("Mcontent");
            fieldSet.add("Mid");
            fieldSet.add("Uid");
            fieldSet.add("Ualais");
            fieldSet.add("Uimage");
            fieldSet.add("Mdatetime");
            for (ScoreDoc scoreDoc : docs.scoreDocs)  
            {  
            	Document document = searcher.doc(scoreDoc.doc,fieldSet); 
            	Message message=new Message();
                System.out.println("Mcontent===="+document.get("Mcontent"));
                
            	String str = highlight.getBestFragment(new MMSegAnalyzer(), "Mcontent",document.get("Mcontent"));
                
            	message.setMcontent(str);
            	message.setMid(Integer.valueOf(document.get("Mid")));
            	Integer uid = Integer.valueOf(document.get("Uid"));
            	String Ualais=document.get("Ualais");
            	String Uimage=document.get("Uimage");
            	Users users=new Users(uid,Ualais,Uimage);
            	
            	message.setUser(users);
            	message.setMdatetime(document.get("Mdatetime"));
            	list.add(message);
            }  
        } catch (ParseException e)  
        {  
            e.printStackTrace();  
        } catch (IOException e)  
        {  
            e.printStackTrace();  
        }  
        return list;
    }  
    
    
    public List<HotWord> Hotword() throws IOException{
    	 List<HotWord>list = new ArrayList<HotWord>();
    	 Directory indexDirectory = FSDirectory.open(new File("d:/index"));
         IndexReader indexReader = DirectoryReader.open(indexDirectory);
         //关键词组==>出现关键词的文章编号、出现次数、起始偏移量、结束偏移量，出现频率
         // 例   中国    2 1 1 1 5
         //    美国    3 3 3 3 3
         Terms terms = MultiFields.getTerms(indexReader, "Mcontent");
         TermsEnum termsEnums = terms.iterator(null);
         BytesRef byteRef = null;
         //
         while ((byteRef = termsEnums.next()) != null) {
        	 //关键词
        	 String term = new String(byteRef.bytes, byteRef.offset,byteRef.length);
             //出现频率
        	 long total=termsEnums.totalTermFreq();
             //System.out.println("term is : " + term);
             //System.out.println(termsEnums.totalTermFreq()); 
             if(total>6&&term.length()>=2){
            	 HotWord hotWord = new HotWord();
            	 hotWord.setTotal(total);
            	 hotWord.setWord(term);
            	 list.add(hotWord);
             }
         }
		return list;
    }
}  
