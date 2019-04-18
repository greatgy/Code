/*
Copyright (c) 2003-2012, CKSource - Frederico Knabben. All rights reserved.
For licensing, see LICENSE.html or http://ckeditor.com/license
*/

CKEDITOR.editorConfig = function( config )
{
	config.fontSize_sizes = '默认/1em;中/1.25em;大/1.714em;特大/2em;极大/3em';
	//config.filebrowserImageBrowseUrl = "";
	config.filebrowserImageUploadUrl = "/ckeditor/img";
//	config.filebrowserFlashBrowseUrl = "";
	config.filebrowserFlashUploadUrl = "/ckeditor/flash";
	config.toolbar = 'MyToolbar';
	config.toolbar_MyToolbar =[
	                       	['Source', 'Preview', '-', 'Templates'],
	                    	['Cut', 'Copy', 'Paste', 'PasteText', 'PasteWord', '-', 'Print'],
	                    	['Undo', 'Redo', '-', 'Find', 'Replace', '-', 'SelectAll', 'RemoveFormat'],
	                    	'/',
	                    	['Bold', 'Italic', 'Underline', 'StrikeThrough', '-', 'Subscript', 'Superscript'],
	                    	['OrderedList', 'UnorderedList', '-', 'Outdent', 'Indent', 'Blockquote', 'CreateDiv'],
	                    	['JustifyLeft', 'JustifyCenter', 'JustifyRight', 'JustifyFull'],
	                    	['Link', 'Unlink', 'Anchor'],
	                    	['Image', 'Flash', 'Table', 'Rule'], ['Smiley', 'SpecialChar', 'PageBreak'],
	                    	'/',
	                    	['Style', 'FontFormat'], ['FontName', 'FontSize'],
	                    	['TextColor', 'BGColor'],
	                    	['FitWindow', 'ShowBlocks']		// No comma for the last row.
	                    ];
	config.toolbar = 'AdminTB';
	config.toolbar_AdminTB =[
	                           ['Source', '-', 'Undo', 'Redo', '-', 'Paste', 'PasteText','PasteFromWord','SelectAll', 'RemoveFormat', '-', 'Find', 'Replace', 'Print', 'Templates'],
	                           ['Bold', 'Italic', 'Underline', 'StrikeThrough', '-', 'NumberedList','BulletedList','Outdent', 'Indent', '-', 'Subscript', 'Superscript'],
	                           ['JustifyLeft', 'JustifyCenter', 'JustifyRight', 'JustifyFull'],
	                           ['Link', 'Unlink', 'Anchor'],
	                           '/',
	                           [ 'Styles','Format','Font','FontSize' ],
	                           ['TextColor', 'BGColor'],
	                           ['Image', 'Flash', 'Table', 'HorizontalRule'], ['SpecialChar', 'PageBreak', 'ShowBlocks'],
	                           ];
	config.toolbar = 'MyToolbarForUser';
	config.toolbar_MyToolbarForUser =[
	                    	['Cut', 'Copy', 'Paste', 'PasteText', 'PasteWord', 'SpecialChar'],
	                    	['Bold', 'Italic', 'Underline', 'StrikeThrough', '-', 'Subscript', 'Superscript'],
	                    	['SelectAll', 'RemoveFormat', 'Replace', 'ShowBlocks'],
	                        ['JustifyLeft', 'JustifyCenter', 'JustifyRight', 'JustifyFull'],
	                    	['OrderedList', 'UnorderedList', '-', 'Outdent', 'Indent'],
	                    	['TextColor', 'BGColor', 'Link', 'Unlink', 'Table'],
	                    	['FontSize','Font', 'Image', 'Flash']
	                    ];
	config.toolbar = 'MyToolbarForUserArticle';
	config.toolbar_MyToolbarForUserArticle = [
	                                  ['RemoveFormat', 'Bold', 'Italic', 'Underline', 'StrikeThrough', 'Subscript', 'Superscript', 'OrderedList', 'UnorderedList'],
	                                  ['JustifyLeft', 'JustifyCenter', 'JustifyRight', 'JustifyFull','Replace', 'Outdent', 'Indent'],
	                                  ['TextColor', 'Link', 'Unlink', 'Table', 'Image'],
	                                  ['FontSize', 'Maximize']
	                                  ];
	config.toolbar = 'MyToolbarSimple';
	config.toolbar_MyToolbarSimple = [
	                              	['Bold', 'Italic', 'Underline', 'StrikeThrough', 'Subscript', 'Superscript', 'OrderedList', 'UnorderedList'],
	                                ['JustifyLeft', 'JustifyCenter', 'JustifyRight', 'JustifyFull'],
	                                ['SelectAll', 'RemoveFormat', 'Replace', 'ShowBlocks', 'Outdent', 'Indent'],
	                            	['TextColor', 'BGColor', 'Link', 'Unlink', 'Table'],
	                            	['FontSize', 'Maximize']
	                            ];
	
	config.toolbar = 'Basic';
	config.toolbar_Basic = [
	                    	['Bold', 'Italic', '-', 'TextColor', 'BGColor', '-','FontSize']
	                    	];
};
