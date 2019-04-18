CKEDITOR.dialog.add('insertcode', function(editor){ 
 var escape = function(value){ 
  return value; 
 }; 
 return { 
  title: 'Insert video url', 
  resizable: CKEDITOR.DIALOG_RESIZE_BOTH, 
  minWidth: 720, 
  minHeight: 480, 
  contents: [{ 
   id: 'cb', 
   name: 'cb', 
   label: 'cb', 
   title: 'cb', 
   elements: [{ 
    type: 'textarea', 
    style: 'width:360px;height:420px', 
    label: 'Code', 
    id: 'code', 
    rows: 31, 
    'default': ''
   }] 
  }], 
  onOk: function(){ 
   code = this.getValueOf('cb', 'code'); 
   html = '' + escape(code) + '';
   html = '<div style="height:400px;" class="flashCodeCss">' + html + '</div>'; 
   editor.insertHtml(html); 
  }, 
  onLoad: function(){ 
  } 
 }; 
});