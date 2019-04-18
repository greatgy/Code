CKEDITOR.plugins.add('musicupload', {    
	requires: ['dialog'],   
	init: function (editor) {        
	var b = editor.addCommand('musicupload', new CKEDITOR.dialogCommand('musicupload'));        
	editor.ui.addButton('Mupload', {      
			label: '上传音乐',        
			command: 'musicupload',           
			icon: this.path + 'images/upm.gif'
			});        
	CKEDITOR.dialog.add('musicupload', this.path + 'dialogs/musicupload.js');
	}
});
