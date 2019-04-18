[#ftl]
<div id="maskcover"></div>
<div class="editContainer" id="editContainer">
	<a href="javascript:;" id="cancelbtn"><img src="${baseimg}/imgs/default/closeimg.png" class="closeimg" /></a>
	<div class="opacitybg"></div>
	<form action="">
		<ul class="editInforbox">
			<li>
				<span>用户名：</span>
				<div class="editable" contenteditable>${me.showname}</div>
				<input id="uid" type="hidden" value="${me.uid}"/>
			</li>
			<li>
				<span>公司：</span>
				<div id="company" class="editable" contenteditable>${me.company}</div>
			</li>
			<li>
				<span>部门：</span>
				<div id="department" class="editable" contenteditable>${me.department}</div>
			</li>
			<li>
				<span>职位：</span>
				<div id="job" class="editable" contenteditable>${me.job}</div>
			</li>
			<li style="border-bottom: none;">
				<span>简介：</span>
				<textarea id="summary">${me.summary}</textarea>
			</li>
		</ul>
		<div class="buttons">
			<button class="cancel">取消</button>
			<button class="save">保存修改</button>
		</div>
	</form>
</div>
<script src="${basejs}/js/pages/mask.js"></script>