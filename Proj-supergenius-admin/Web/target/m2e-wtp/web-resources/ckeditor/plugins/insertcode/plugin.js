CKEDITOR.plugins.add('insertcode', {
requires: ['dialog'], 
init: function(a){
a.addCommand('insertcode', new CKEDITOR.dialogCommand('insertcode'));
a.ui.addButton('Insertcode', {
label: "video url", command: 'insertcode',icon: this.path + '/images/code.png'
});
CKEDITOR.dialog.add('insertcode', this.path + '/dialogs/insertcode.js'); 
}
});