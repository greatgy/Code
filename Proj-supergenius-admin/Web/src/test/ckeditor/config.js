/**
 * @license Copyright (c) 2003-2015, CKSource - Frederico Knabben. All rights
 *          reserved. For licensing, see LICENSE.md or
 *          http://ckeditor.com/license
 */

CKEDITOR.editorConfig = function( config ) {
	config.fontSize_sizes = '初号8/3.5em;小初/3em;一号/2.125em;小一/2em;二号/1.8125em;小二/1.5em;三号/1.3125em;小三/1.25em;四号/1.125em;小四/1em;五号/0.875em;小五/0.75em;六号/0.625em;小六/0.5em;七号/0.4375em;';
	config.filebrowserImageBrowseUrl = "";
	config.filebrowserImageUploadUrl = "/ckeditor/img";
	config.filebrowserFlashBrowseUrl = "";
	config.filebrowserFlashUploadUrl = "/ckeditor/flash";
	config.language = 'zh-cn';
	config.pasteFromWordIgnoreFontFace = true;
	config.pasteFromWordKeepsStructure = false;
	config.pasteFromWordRemoveStyle = false;
	config.selectMultiple = true;
	config.toolbar = 'MyToolbar';
	config.toolbar_MyToolbar = [
			[ 'Cut', 'Copy', 'Paste', 'PasteText', 'PasteWord' ],
			[ 'Undo', 'Redo', '-', 'Find', 'Replace', '-', 'SelectAll' ], '/',
			[ 'Bold', 'Italic', 'Underline', '-', 'Subscript', 'Superscript' ],
			[ 'Outdent', 'Indent', 'Blockquote', 'CreateDiv' ],
			[ 'JustifyLeft', 'JustifyCenter', 'JustifyRight' ],
			[ 'Link', 'Unlink', 'Anchor' ], [ 'Image', 'MultiImg', 'Table' ],
			[ 'SpecialChar', 'PageBreak' ], '/', [ 'Style', 'FontFormat' ],
			[ 'FontName', 'FontSize' ], [ 'TextColor', 'BGColor' ],
			[ 'Maximize', 'ShowBlocks' ] 
	];
	config.toolbar = 'AdminTB';
	config.toolbar_AdminTB = [
			[ 'Source', '-', 'Undo', 'Redo', '-', 'Paste', 'PasteText',
					'PasteFromWord', 'SelectAll', 'RemoveFormat', '-', 'Find',
					'Replace', 'Print', 'Templates' ],
			[ 'Bold', 'Italic', 'Underline', 'StrikeThrough', '-',
					'NumberedList', 'BulletedList', 'Outdent', 'Indent', '-',
					'Subscript', 'Superscript' ],
			[ 'JustifyLeft', 'JustifyCenter', 'JustifyRight', 'JustifyFull' ],
			[ 'Link', 'Unlink', 'Anchor' ], '/',
			[ 'Styles', 'Format', 'Font', 'FontSize' ],
			[ 'TextColor', 'BGColor' ],
			[ 'Image', 'Flash', 'Table', 'HorizontalRule' ],
			[ 'SpecialChar', 'PageBreak', 'ShowBlocks' ], ];
	config.toolbar = 'MyToolbarForUser';
	config.toolbar_MyToolbarForUser = [
			[ 'Cut', 'Copy', 'Paste', 'PasteText', 'PasteWord', 'SpecialChar' ],
			[ 'Bold', 'Italic', 'Underline', 'StrikeThrough', '-', 'Subscript',
					'Superscript' ],
			[ 'SelectAll', 'RemoveFormat', 'Replace', 'ShowBlocks' ],
			[ 'JustifyLeft', 'JustifyCenter', 'JustifyRight', 'JustifyFull' ],
			[ 'OrderedList', 'UnorderedList', '-', 'Outdent', 'Indent' ],
			[ 'TextColor', 'BGColor', 'Link', 'Unlink', 'Table' ],
			[ 'FontSize', 'Font', 'Image', 'Flash' ] ];
	config.toolbar = 'MyToolbarForUserArticle';
	config.toolbar_MyToolbarForUserArticle = [
			[ 'RemoveFormat', 'Bold', 'Italic', 'Underline', 'StrikeThrough',
					'Subscript', 'Superscript', 'OrderedList', 'UnorderedList' ],
			[ 'JustifyLeft', 'JustifyCenter', 'JustifyRight', 'JustifyFull',
					'Replace', 'Outdent', 'Indent' ],
			[ 'TextColor', 'Link', 'Unlink', 'Table', 'Image' ],
			[ 'FontSize', 'Maximize' ] ];
	config.toolbar = 'MyToolbarSimple';
	config.toolbar_MyToolbarSimple = [
			[ 'Bold', 'Italic', 'Underline', 'StrikeThrough', 'Subscript',
					'Superscript', 'OrderedList', 'UnorderedList' ],
			[ 'JustifyLeft', 'JustifyCenter', 'JustifyRight', 'JustifyFull' ],
			[ 'SelectAll', 'RemoveFormat', 'Replace', 'ShowBlocks', 'Outdent',
					'Indent' ],
			[ 'TextColor', 'BGColor', 'Link', 'Unlink', 'Table' ],
			[ 'FontSize', 'Maximize' ] ];

	config.toolbar = 'Basic';
	config.toolbar_Basic = [ [ 'Bold', 'Italic', '-', 'TextColor', 'BGColor',
			'-', 'FontSize' ] ];
	// Define changes to default configuration here. For example:
	// config.language = 'fr';
	// config.uiColor = '#AADC6E';
	config.extraPlugins = 'lineheight';
	config.line_height = '1em;1.5em;2em;2.5em;3em;3.5em;4em;4.5em;5em';
};
