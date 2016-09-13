/*
 tableExport.jquery.plugin

 Copyright (c) 2015,2016 hhurz, https://github.com/hhurz/tableExport.jquery.plugin
 Original work Copyright (c) 2014 Giri Raj, https://github.com/kayalshri/

 Licensed under the MIT License, http://opensource.org/licenses/mit-license
*/
(function(d){d.fn.extend({tableExport:function(t){function P(b){var a=[];d(b).find("thead").first().find("th").each(function(b,f){void 0!==d(f).attr("data-field")&&(a[b]=d(f).attr("data-field"))});return a}function y(b,h,e,f,k){if(-1==d.inArray(e,a.ignoreRow)&&-1==d.inArray(e-f,a.ignoreRow)){var B=d(b).filter(function(){return"none"!=d(this).data("tableexport-display")&&(d(this).is(":visible")||"always"==d(this).data("tableexport-display")||"always"==d(this).closest("table").data("tableexport-display"))}).find(h),
Q=0;B.each(function(b){if("always"==d(this).data("tableexport-display")||"none"!=d(this).css("display")&&"hidden"!=d(this).css("visibility")&&"none"!=d(this).data("tableexport-display")){var h=b,f=!1;0<a.ignoreColumn.length&&("string"==typeof a.ignoreColumn[0]?H.length>h&&"undefined"!=typeof H[h]&&-1!=d.inArray(H[h],a.ignoreColumn)&&(f=!0):"number"!=typeof a.ignoreColumn[0]||-1==d.inArray(h,a.ignoreColumn)&&-1==d.inArray(h-B.length,a.ignoreColumn)||(f=!0));if(0==f&&"function"===typeof k){var f=0,
g,l=0;if("undefined"!=typeof v[e]&&0<v[e].length)for(h=0;h<=b;h++)"undefined"!=typeof v[e][h]&&(k(null,e,h),delete v[e][h],b++);d(this).is("[colspan]")&&(f=parseInt(d(this).attr("colspan")),Q+=0<f?f-1:0);d(this).is("[rowspan]")&&(l=parseInt(d(this).attr("rowspan")));k(this,e,b);for(h=0;h<f-1;h++)k(null,e,b+h);if(l)for(g=1;g<l;g++)for("undefined"==typeof v[e+g]&&(v[e+g]=[]),v[e+g][b+Q]="",h=1;h<f;h++)v[e+g][b+Q-h]=""}}});if("undefined"!=typeof v[e]&&0<v[e].length)for(c=0;c<=v[e].length;c++)"undefined"!=
typeof v[e][c]&&(k(null,e,c),delete v[e][c])}}function T(b){!0===a.consoleLog&&console.log(b.output());if("string"===a.outputMode)return b.output();if("base64"===a.outputMode)return E(b.output());try{var h=b.output("blob");saveAs(h,a.fileName+".pdf")}catch(e){G(a.fileName+".pdf","data:application/pdf;base64,",b.output())}}function U(b,a,e){var f=0;"undefined"!=typeof e&&(f=e.colspan);if(0<=f){for(var k=b.width,d=b.textPos.x,g=a.table.columns.indexOf(a.column),l=1;l<f;l++)k+=a.table.columns[g+l].width;
1<f&&("right"===b.styles.halign?d=b.textPos.x+k-b.width:"center"===b.styles.halign&&(d=b.textPos.x+(k-b.width)/2));b.width=k;b.textPos.x=d;"undefined"!=typeof e&&1<e.rowspan&&(b.height*=e.rowspan);if("middle"===b.styles.valign||"bottom"===b.styles.valign)e=("string"===typeof b.text?b.text.split(/\r\n|\r|\n/g):b.text).length||1,2<e&&(b.textPos.y-=(2-1.15)/2*a.row.styles.fontSize*(e-2)/3);return!0}return!1}function V(b,h,e){h.each(function(){var h=d(this).children();if(d(this).is("div")){var k=L(C(this,
"background-color"),[255,255,255]),B=L(C(this,"border-top-color"),[0,0,0]),g=M(this,"border-top-width",a.jspdf.unit),l=this.getBoundingClientRect(),m=this.offsetLeft*e.dw,n=this.offsetTop*e.dh,p=l.width*e.dw,l=l.height*e.dh;e.doc.setDrawColor.apply(void 0,B);e.doc.setFillColor.apply(void 0,k);e.doc.setLineWidth(g);e.doc.rect(b.x+m,b.y+n,p,l,g?"FD":"F")}"undefined"!=typeof h&&0<h.length&&V(b,h,e)})}function R(b,a,e){return b.replace(new RegExp(a.replace(/([.*+?^=!:${}()|\[\]\/\\])/g,"\\$1"),"g"),e)}
function aa(b){b=R(b||"0",a.numbers.html.decimalMark,".");b=R(b,a.numbers.html.thousandsSeparator,"");return"number"===typeof b||!1!==jQuery.isNumeric(b)?b:!1}function w(b,h,e){var f="";if(null!=b){b=d(b);var k;k=b[0].hasAttribute("data-tableexport-value")?b.data("tableexport-value"):b.html();"function"===typeof a.onCellHtmlData&&(k=a.onCellHtmlData(b,h,e,k));if(!0===a.htmlContent)f=d.trim(k);else{var B=k.replace(/\n/g,"\u2028").replace(/<br\s*[\/]?>/gi,"\u2060");k=d("<div/>").html(B).contents();
B="";d.each(k.text().split("\u2028"),function(b,a){0<b&&(B+=" ");B+=d.trim(a)});d.each(B.split("\u2060"),function(b,a){0<b&&(f+="\n");f+=d.trim(a).replace(/\u00AD/g,"")});if(a.numbers.html.decimalMark!=a.numbers.output.decimalMark||a.numbers.html.thousandsSeparator!=a.numbers.output.thousandsSeparator)if(k=aa(f),!1!==k){var g=(""+k).split(".");1==g.length&&(g[1]="");var l=3<g[0].length?g[0].length%3:0,f=(0>k?"-":"")+(a.numbers.output.thousandsSeparator?(l?g[0].substr(0,l)+a.numbers.output.thousandsSeparator:
"")+g[0].substr(l).replace(/(\d{3})(?=\d)/g,"$1"+a.numbers.output.thousandsSeparator):g[0])+(g[1].length?a.numbers.output.decimalMark+g[1]:"")}}!0===a.escape&&(f=escape(f));"function"===typeof a.onCellData&&(f=a.onCellData(b,h,e,f))}return f}function ba(b,a,e){return a+"-"+e.toLowerCase()}function L(b,a){var e=/^rgb\((\d{1,3}),\s*(\d{1,3}),\s*(\d{1,3})\)$/.exec(b),f=a;e&&(f=[parseInt(e[1]),parseInt(e[2]),parseInt(e[3])]);return f}function W(b){var a=C(b,"text-align"),e=C(b,"font-weight"),f=C(b,"font-style"),
k="";"start"==a&&(a="rtl"==C(b,"direction")?"right":"left");700<=e&&(k="bold");"italic"==f&&(k+=f);""==k&&(k="normal");a={style:{align:a,bcolor:L(C(b,"background-color"),[255,255,255]),color:L(C(b,"color"),[0,0,0]),fstyle:k},colspan:parseInt(d(b).attr("colspan"))||0,rowspan:parseInt(d(b).attr("rowspan"))||0};null!==b&&(b=b.getBoundingClientRect(),a.rect={width:b.width,height:b.height});return a}function C(b,a){try{return window.getComputedStyle?(a=a.replace(/([a-z])([A-Z])/,ba),window.getComputedStyle(b,
null).getPropertyValue(a)):b.currentStyle?b.currentStyle[a]:b.style[a]}catch(e){}return""}function M(a,h,e){h=C(a,h).match(/\d+/);if(null!==h){h=h[0];a=a.parentElement;var f=document.createElement("div");f.style.overflow="hidden";f.style.visibility="hidden";a.appendChild(f);f.style.width=100+e;e=100/f.offsetWidth;a.removeChild(f);return h*e}return 0}function G(a,h,e){var f=window.navigator.userAgent;if(0<f.indexOf("MSIE ")||f.match(/Trident.*rv\:11\./)){if(h=document.createElement("iframe"))document.body.appendChild(h),
h.setAttribute("style","display:none"),h.contentDocument.open("txt/html","replace"),h.contentDocument.write(e),h.contentDocument.close(),h.focus(),h.contentDocument.execCommand("SaveAs",!0,a),document.body.removeChild(h)}else if(f=document.createElement("a")){f.style.display="none";f.download=a;0<=h.toLowerCase().indexOf("base64,")?f.href=h+E(e):f.href=h+encodeURIComponent(e);document.body.appendChild(f);if(document.createEvent)null==N&&(N=document.createEvent("MouseEvents")),N.initEvent("click",
!0,!1),f.dispatchEvent(N);else if(document.createEventObject)f.fireEvent("onclick");else if("function"==typeof f.onclick)f.onclick();document.body.removeChild(f)}}function E(a){var h="",e,f,k,d,g,l,m=0;a=a.replace(/\x0d\x0a/g,"\n");f="";for(k=0;k<a.length;k++)d=a.charCodeAt(k),128>d?f+=String.fromCharCode(d):(127<d&&2048>d?f+=String.fromCharCode(d>>6|192):(f+=String.fromCharCode(d>>12|224),f+=String.fromCharCode(d>>6&63|128)),f+=String.fromCharCode(d&63|128));for(a=f;m<a.length;)e=a.charCodeAt(m++),
f=a.charCodeAt(m++),k=a.charCodeAt(m++),d=e>>2,e=(e&3)<<4|f>>4,g=(f&15)<<2|k>>6,l=k&63,isNaN(f)?g=l=64:isNaN(k)&&(l=64),h=h+"ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=".charAt(d)+"ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=".charAt(e)+"ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=".charAt(g)+"ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=".charAt(l);return h}var a={consoleLog:!1,csvEnclosure:'"',csvSeparator:",",csvUseBOM:!0,
displayTableName:!1,escape:!1,excelstyles:[],fileName:"tableExport",htmlContent:!1,ignoreColumn:[],ignoreRow:[],jsonScope:"all",jspdf:{orientation:"p",unit:"pt",format:"a4",margins:{left:20,right:10,top:10,bottom:10},autotable:{styles:{cellPadding:2,rowHeight:12,fontSize:8,fillColor:255,textColor:50,fontStyle:"normal",overflow:"ellipsize",halign:"left",valign:"middle"},headerStyles:{fillColor:[52,73,94],textColor:255,fontStyle:"bold",halign:"center"},alternateRowStyles:{fillColor:245},tableExport:{onAfterAutotable:null,
onBeforeAutotable:null,onTable:null}}},numbers:{html:{decimalMark:".",thousandsSeparator:","},output:{decimalMark:".",thousandsSeparator:","}},onCellData:null,onCellHtmlData:null,outputMode:"file",tbodySelector:"tr",theadSelector:"tr",tableName:"myTableName",type:"csv",worksheetName:"xlsWorksheetName"},u=this,N=null,r=[],q=[],n=0,v=[],l="",H=[];d.extend(!0,a,t);H=P(u);if("csv"==a.type||"txt"==a.type){t=function(b,h,e,f){q=d(u).find(b).first().find(h);q.each(function(){l="";y(this,e,n,f+q.length,function(b,
e,d){var h=l,f="";if(null!=b)if(b=w(b,e,d),e=null===b||""==b?"":b.toString(),b instanceof Date)f=a.csvEnclosure+b.toLocaleString()+a.csvEnclosure;else if(f=R(e,a.csvEnclosure,a.csvEnclosure+a.csvEnclosure),0<=f.indexOf(a.csvSeparator)||/[\r\n ]/g.test(f))f=a.csvEnclosure+f+a.csvEnclosure;l=h+(f+a.csvSeparator)});l=d.trim(l).substring(0,l.length-1);0<l.length&&(0<D.length&&(D+="\n"),D+=l);n++});return q.length};var D="",z=0,n=0,z=z+t("thead",a.theadSelector,"th,td",z),z=z+t("tbody",a.tbodySelector,
"td",z);t("tfoot",a.tbodySelector,"td",z);D+="\n";!0===a.consoleLog&&console.log(D);if("string"===a.outputMode)return D;if("base64"===a.outputMode)return E(D);try{var A=new Blob([D],{type:"text/"+("csv"==a.type?"csv":"plain")+";charset=utf-8"});saveAs(A,a.fileName+"."+a.type,"csv"!=a.type||!1===a.csvUseBOM)}catch(b){G(a.fileName+"."+a.type,"data:text/"+("csv"==a.type?"csv":"plain")+";charset=utf-8,"+("csv"==a.type&&a.csvUseBOM?"\ufeff":""),D)}}else if("sql"==a.type){var n=0,p="INSERT INTO `"+a.tableName+
"` (",r=d(u).find("thead").first().find(a.theadSelector);r.each(function(){y(this,"th,td",n,r.length,function(a,d,e){p+="'"+w(a,d,e)+"',"});n++;p=d.trim(p);p=d.trim(p).substring(0,p.length-1)});p+=") VALUES ";q=d(u).find("tbody").first().find(a.tbodySelector);q.each(function(){l="";y(this,"td",n,r.length+q.length,function(a,d,e){l+="'"+w(a,d,e)+"',"});3<l.length&&(p+="("+l,p=d.trim(p).substring(0,p.length-1),p+="),");n++});p=d.trim(p).substring(0,p.length-1);p+=";";!0===a.consoleLog&&console.log(p);
if("string"===a.outputMode)return p;if("base64"===a.outputMode)return E(p);try{A=new Blob([p],{type:"text/plain;charset=utf-8"}),saveAs(A,a.fileName+".sql")}catch(b){G(a.fileName+".sql","data:application/sql;charset=utf-8,",p)}}else if("json"==a.type){var I=[],r=d(u).find("thead").first().find(a.theadSelector);r.each(function(){var a=[];y(this,"th,td",n,r.length,function(d,e,f){a.push(w(d,e,f))});I.push(a)});var S=[],q=d(u).find("tbody").first().find(a.tbodySelector);q.each(function(){var a={},h=
0;y(this,"td",n,r.length+q.length,function(e,d,k){I.length?a[I[I.length-1][h]]=w(e,d,k):a[h]=w(e,d,k);h++});0==d.isEmptyObject(a)&&S.push(a);n++});t="";t="head"==a.jsonScope?JSON.stringify(I):"data"==a.jsonScope?JSON.stringify(S):JSON.stringify({header:I,data:S});!0===a.consoleLog&&console.log(t);if("string"===a.outputMode)return t;if("base64"===a.outputMode)return E(t);try{A=new Blob([t],{type:"application/json;charset=utf-8"}),saveAs(A,a.fileName+".json")}catch(b){G(a.fileName+".json","data:application/json;charset=utf-8;base64,",
t)}}else if("xml"===a.type){var n=0,x='<?xml version="1.0" encoding="utf-8"?>',x=x+"<tabledata><fields>",r=d(u).find("thead").first().find(a.theadSelector);r.each(function(){y(this,"th,td",n,q.length,function(a,d,e){x+="<field>"+w(a,d,e)+"</field>"});n++});var x=x+"</fields><data>",X=1,q=d(u).find("tbody").first().find(a.tbodySelector);q.each(function(){var a=1;l="";y(this,"td",n,r.length+q.length,function(d,e,f){l+="<column-"+a+">"+w(d,e,f)+"</column-"+a+">";a++});0<l.length&&"<column-1></column-1>"!=
l&&(x+='<row id="'+X+'">'+l+"</row>",X++);n++});x+="</data></tabledata>";!0===a.consoleLog&&console.log(x);if("string"===a.outputMode)return x;if("base64"===a.outputMode)return E(x);try{A=new Blob([x],{type:"application/xml;charset=utf-8"}),saveAs(A,a.fileName+".xml")}catch(b){G(a.fileName+".xml","data:application/xml;charset=utf-8;base64,",x)}}else if("excel"==a.type||"xls"==a.type||"word"==a.type||"doc"==a.type){t="excel"==a.type||"xls"==a.type?"excel":"word";var z="excel"==t?"xls":"doc",m="xls"==
z?'xmlns:x="urn:schemas-microsoft-com:office:excel"':'xmlns:w="urn:schemas-microsoft-com:office:word"',F="";d(u).filter(function(){return"none"!=d(this).data("tableexport-display")&&(d(this).is(":visible")||"always"==d(this).data("tableexport-display"))}).each(function(){n=0;H=P(this);F+="<table><thead>";r=d(this).find("thead").first().find(a.theadSelector);r.each(function(){l="";y(this,"th,td",n,r.length,function(b,h,e){if(null!=b){var f="";l+="<th";for(var k in a.excelstyles)if(a.excelstyles.hasOwnProperty(k)){var g=
d(b).css(a.excelstyles[k]);""!=g&&"0px none rgb(0, 0, 0)"!=g&&(""==f&&(f='style="'),f+=a.excelstyles[k]+":"+g+";")}""!=f&&(l+=" "+f+'"');d(b).is("[colspan]")&&(l+=' colspan="'+d(b).attr("colspan")+'"');d(b).is("[rowspan]")&&(l+=' rowspan="'+d(b).attr("rowspan")+'"');l+=">"+w(b,h,e)+"</th>"}});0<l.length&&(F+="<tr>"+l+"</tr>");n++});F+="</thead><tbody>";q=d(this).find("tbody").first().find(a.tbodySelector);q.each(function(){l="";y(this,"td",n,r.length+q.length,function(b,h,e){if(null!=b){var f="",
k=d(b).data("tableexport-msonumberformat");"undefined"==typeof k&&"function"===typeof a.onMsoNumberFormat&&(k=a.onMsoNumberFormat(b,h,e));"undefined"!=typeof k&&""!=k&&(f='style="mso-number-format:'+k+";");l+="<td";for(var g in a.excelstyles)a.excelstyles.hasOwnProperty(g)&&(k=d(b).css(a.excelstyles[g]),""!=k&&"0px none rgb(0, 0, 0)"!=k&&(""==f&&(f='style="'),f+=a.excelstyles[g]+":"+k+";"));""!=f&&(l+=" "+f+'"');d(b).is("[colspan]")&&(l+=' colspan="'+d(b).attr("colspan")+'"');d(b).is("[rowspan]")&&
(l+=' rowspan="'+d(b).attr("rowspan")+'"');l+=">"+w(b,h,e)+"</td>"}});0<l.length&&(F+="<tr>"+l+"</tr>");n++});a.displayTableName&&(F+="<tr><td></td></tr><tr><td></td></tr><tr><td>"+w(d("<p>"+a.tableName+"</p>"))+"</td></tr>");F+="</tbody></table>";!0===a.consoleLog&&console.log(F)});m='<html xmlns:o="urn:schemas-microsoft-com:office:office" '+m+' xmlns="http://www.w3.org/TR/REC-html40">'+('<meta http-equiv="content-type" content="application/vnd.ms-'+t+'; charset=UTF-8">')+"<head>";"excel"===t&&(m+=
"\x3c!--[if gte mso 9]>",m+="<xml>",m+="<x:ExcelWorkbook>",m+="<x:ExcelWorksheets>",m+="<x:ExcelWorksheet>",m+="<x:Name>",m+=a.worksheetName,m+="</x:Name>",m+="<x:WorksheetOptions>",m+="<x:DisplayGridlines/>",m+="</x:WorksheetOptions>",m+="</x:ExcelWorksheet>",m+="</x:ExcelWorksheets>",m+="</x:ExcelWorkbook>",m+="</xml>",m+="<![endif]--\x3e");m+="</head>";m+="<body>";m+=F;m+="</body>";m+="</html>";!0===a.consoleLog&&console.log(m);if("string"===a.outputMode)return m;if("base64"===a.outputMode)return E(m);
try{A=new Blob([m],{type:"application/vnd.ms-"+a.type}),saveAs(A,a.fileName+"."+z)}catch(b){G(a.fileName+"."+z,"data:application/vnd.ms-"+t+";base64,",m)}}else if("png"==a.type)html2canvas(d(u)[0],{allowTaint:!0,background:"#fff",onrendered:function(b){b=b.toDataURL();b=b.substring(22);for(var d=atob(b),e=new ArrayBuffer(d.length),f=new Uint8Array(e),k=0;k<d.length;k++)f[k]=d.charCodeAt(k);!0===a.consoleLog&&console.log(d);if("string"===a.outputMode)return d;if("base64"===a.outputMode)return E(b);
try{var g=new Blob([e],{type:"image/png"});saveAs(g,a.fileName+".png")}catch(l){G(a.fileName+".png","data:image/png;base64,",b)}}});else if("pdf"==a.type)if(!1===a.jspdf.autotable){var A={dim:{w:M(d(u).first().get(0),"width","mm"),h:M(d(u).first().get(0),"height","mm")},pagesplit:!1},Y=new jsPDF(a.jspdf.orientation,a.jspdf.unit,a.jspdf.format);Y.addHTML(d(u).first(),a.jspdf.margins.left,a.jspdf.margins.top,A,function(){T(Y)})}else{var g=a.jspdf.autotable.tableExport;if("string"===typeof a.jspdf.format&&
"bestfit"===a.jspdf.format.toLowerCase()){var J={a0:[2383.94,3370.39],a1:[1683.78,2383.94],a2:[1190.55,1683.78],a3:[841.89,1190.55],a4:[595.28,841.89]},O="",K="",Z=0;d(u).filter(":visible").each(function(){if("none"!=d(this).css("display")){var a=M(d(this).get(0),"width","pt");if(a>Z){a>J.a0[0]&&(O="a0",K="l");for(var g in J)J.hasOwnProperty(g)&&J[g][1]>a&&(O=g,K="l",J[g][0]>a&&(K="p"));Z=a}}});a.jspdf.format=""==O?"a4":O;a.jspdf.orientation=""==K?"w":K}g.doc=new jsPDF(a.jspdf.orientation,a.jspdf.unit,
a.jspdf.format);d(u).filter(function(){return"none"!=d(this).data("tableexport-display")&&(d(this).is(":visible")||"always"==d(this).data("tableexport-display"))}).each(function(){var b,h=0;H=P(this);g.columns=[];g.rows=[];g.rowoptions={};if("function"===typeof g.onTable&&!1===g.onTable(d(this),a))return!0;a.jspdf.autotable.tableExport=null;var e=d.extend(!0,{},a.jspdf.autotable);a.jspdf.autotable.tableExport=g;e.margin={};d.extend(!0,e.margin,a.jspdf.margins);e.tableExport=g;"function"!==typeof e.beforePageContent&&
(e.beforePageContent=function(a){1==a.pageCount&&a.table.rows.concat(a.table.headerRow).forEach(function(b){0<b.height&&(b.height+=(2-1.15)/2*b.styles.fontSize,a.table.height+=(2-1.15)/2*b.styles.fontSize)})});"function"!==typeof e.createdHeaderCell&&(e.createdHeaderCell=function(a,b){a.styles=d.extend({},b.row.styles);if("undefined"!=typeof g.columns[b.column.dataKey]){var f=g.columns[b.column.dataKey];if("undefined"!=typeof f.rect){var h;a.contentWidth=f.rect.width;if("undefined"==typeof g.heightRatio||
0==g.heightRatio)h=b.row.raw[b.column.dataKey].rowspan?b.row.raw[b.column.dataKey].rect.height/b.row.raw[b.column.dataKey].rowspan:b.row.raw[b.column.dataKey].rect.height,g.heightRatio=a.styles.rowHeight/h;h=b.row.raw[b.column.dataKey].rect.height*g.heightRatio;h>a.styles.rowHeight&&(a.styles.rowHeight=h)}"undefined"!=typeof f.style&&!0!==f.style.hidden&&(a.styles.halign=f.style.align,"inherit"===e.styles.fillColor&&(a.styles.fillColor=f.style.bcolor),"inherit"===e.styles.textColor&&(a.styles.textColor=
f.style.color),"inherit"===e.styles.fontStyle&&(a.styles.fontStyle=f.style.fstyle))}});"function"!==typeof e.createdCell&&(e.createdCell=function(a,b){var d=g.rowoptions[b.row.index+":"+b.column.dataKey];"undefined"!=typeof d&&"undefined"!=typeof d.style&&!0!==d.style.hidden&&(a.styles.halign=d.style.align,"inherit"===e.styles.fillColor&&(a.styles.fillColor=d.style.bcolor),"inherit"===e.styles.textColor&&(a.styles.textColor=d.style.color),"inherit"===e.styles.fontStyle&&(a.styles.fontStyle=d.style.fstyle))});
"function"!==typeof e.drawHeaderCell&&(e.drawHeaderCell=function(a,b){var d=g.columns[b.column.dataKey];return(1!=d.style.hasOwnProperty("hidden")||!0!==d.style.hidden)&&0<=d.rowIndex?U(a,b,d):!1});"function"!==typeof e.drawCell&&(e.drawCell=function(a,b){var d=g.rowoptions[b.row.index+":"+b.column.dataKey];if(U(a,b,d)){g.doc.rect(a.x,a.y,a.width,a.height,a.styles.fillStyle);if("undefined"!=typeof d&&"undefined"!=typeof d.kids&&0<d.kids.length){var e=a.height/d.rect.height;if(e>g.dh||"undefined"==
typeof g.dh)g.dh=e;g.dw=a.width/d.rect.width;V(a,d.kids,g)}g.doc.autoTableText(a.text,a.textPos.x,a.textPos.y,{halign:a.styles.halign,valign:a.styles.valign})}return!1});g.headerrows=[];r=d(this).find("thead").find(a.theadSelector);r.each(function(){b=0;g.headerrows[h]=[];y(this,"th,td",h,r.length,function(a,d,e){var f=W(a);f.title=w(a,d,e);f.key=b++;f.rowIndex=h;g.headerrows[h].push(f)});h++});0<h&&d.each(g.headerrows[h-1],function(){obj=1<h&&null==this.rect?g.headerrows[h-2][this.key]:this;null!=
obj&&g.columns.push(obj)});var f=0;q=d(this).find("tbody").find(a.tbodySelector);q.each(function(){var a=[];b=0;y(this,"td",h,r.length+q.length,function(e,h,l){if("undefined"===typeof g.columns[b]){var m={title:"",key:b,style:{hidden:!0}};g.columns.push(m)}"undefined"!==typeof e&&null!=e?(m=W(e),m.kids=d(e).children()):(m=d.extend(!0,{},g.rowoptions[f+":"+(b-1)]),m.colspan=-1);g.rowoptions[f+":"+b++]=m;a.push(w(e,h,l))});a.length&&(g.rows.push(a),f++);h++});if("function"===typeof g.onBeforeAutotable)g.onBeforeAutotable(d(this),
g.columns,g.rows,e);g.doc.autoTable(g.columns,g.rows,e);if("function"===typeof g.onAfterAutotable)g.onAfterAutotable(d(this),e);a.jspdf.autotable.startY=g.doc.autoTableEndPosY()+e.margin.top});T(g.doc);"undefined"!=typeof g.headerrows&&(g.headerrows.length=0);"undefined"!=typeof g.columns&&(g.columns.length=0);"undefined"!=typeof g.rows&&(g.rows.length=0);delete g.doc;g.doc=null}return this}})})(jQuery);