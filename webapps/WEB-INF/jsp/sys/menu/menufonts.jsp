<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<style>
a.fonts {display:block;width:160px;padding:0 10px;float:left;height:38px;line-height:38px;border-radius:3px;}
a.fonts:hover{background:#0f4c0f;color:#fff;font-size:14px;text-decoration: none;}
</style>
<script>
	$('.fonts').click(function(){
		$('#MenuIcon').data('icon',$.trim($(this).text()));
		var icon=$('#MenuIcon').data('icon')
		$('#MenuIcon').removeClass().addClass('fa fa-'+icon).siblings('button').removeClass('hide');
		$(this).dialog('closeCurrent');
	});
	$('#MenuIcon').siblings('button').click(function(){
		var _this=this;
		$(this).alertmsg('confirm','确定要删除图标吗？',{okCall:function(){
			$('#MenuIcon').removeClass();
			$(_this).addClass('hide');
		}})
		
	})
</script>
<!--菜单图形 -->
<div class="bjui-pageContent clearfix">
	<a href="javascript:;" class="fonts"><i class="fa fa-glass"></i> glass</a>
	<a href="javascript:;" class="fonts"><i class="fa fa-heart"></i> heart</a>
	<a href="javascript:;" class="fonts"><i class="fa fa-heart-o"></i> heart-o</a>
	<a href="javascript:;" class="fonts"><i class="fa fa-film"></i> film</a>
	<a href="javascript:;" class="fonts"><i class="fa fa-home"></i> home</a>

	<a href="javascript:;" class="fonts"><i class="fa fa-lock"></i> lock</a>
	<a href="javascript:;" class="fonts"><i class="fa fa-unlock"></i> unlock</a>
	<a href="javascript:;" class="fonts"><i class="fa fa-user"></i> user</a>
	<a href="javascript:;" class="fonts"><i class="fa fa-users"></i> users</a>
	<a href="javascript:;" class="fonts"><i class="fa fa-user-plus"></i> user-plus</a>

	<a href="javascript:;" class="fonts"><i class="fa fa-mobile"></i> mobile</a>
	<a href="javascript:;" class="fonts"><i class="fa fa-eye"></i> eye</a>
	<a href="javascript:;" class="fonts"><i class="fa fa-key"></i> key</a>
	<a href="javascript:;" class="fonts"><i class="fa fa-phone"></i> phone</a>
	<a href="javascript:;" class="fonts"><i class="fa fa-credit-card"></i> credit-card</a>

	<a href="javascript:;" class="fonts"><i class="fa fa-bell"></i> bell</a>
	<a href="javascript:;" class="fonts"><i class="fa fa-bell-o"></i> bell-o</a>
	<a href="javascript:;" class="fonts"><i class="fa fa-area-chart"></i> area-chart</a>
	<a href="javascript:;" class="fonts"><i class="fa fa-bar-chart"></i> bar-chart</a>
	<a href="javascript:;" class="fonts"><i class="fa fa-line-chart"></i> line-chart</a>

	<a href="javascript:;" class="fonts"><i class="fa fa-pie-chart"></i> pie-chart</a>
	<a href="javascript:;" class="fonts"><i class="fa fa-circle"></i> circle</a>
	<a href="javascript:;" class="fonts"><i class="fa fa-circle-o"></i> circle-o</a>
	<a href="javascript:;" class="fonts"><i class="fa fa-circle-thin"></i> circle-thin</a>
	<a href="javascript:;" class="fonts"><i class="fa fa-circle-o-notch"></i> circle-o-notch</a>

	<a href="javascript:;" class="fonts"><i class="fa fa-file"></i> file</a>
	<a href="javascript:;" class="fonts"><i class="fa fa-file-excel-o"></i> file-excel-o</a>
	<a href="javascript:;" class="fonts"><i class="fa fa-file-pdf-o"></i> file-pdf-o</a>
	<a href="javascript:;" class="fonts"><i class="fa fa-file-word-o"></i> file-excel-o</a>
	<a href="javascript:;" class="fonts"><i class="fa fa-file-image-o"></i> file-image-o</a>
	
	<a href="javascript:;" class="fonts"><i class="fa fa-file-o"></i> file-o</a>
	<a href="javascript:;" class="fonts"><i class="fa fa-file-powerpoint-o"></i> file-powerpoint-o</a>
	<a href="javascript:;" class="fonts"><i class="fa fa-file-text"></i> file-text</a>
	<a href="javascript:;" class="fonts"><i class="fa fa-file-text-o"></i> file-text-o</a>
	<a href="javascript:;" class="fonts"><i class="fa fa-files-o"></i> files-o</a>

	<a href="javascript:;" class="fonts"><i class="fa fa-thumbs-up"></i> thumbs-up</a>
	<a href="javascript:;" class="fonts"><i class="fa fa-thumbs-down"></i> thumbs-down</a>
	<a href="javascript:;" class="fonts"><i class="fa fa-thumbs-o-down"></i> thumbs-o-down</a>
	<a href="javascript:;" class="fonts"><i class="fa fa-thumbs-o-up"></i> thumbs-o-up</a>
	<a href="javascript:;" class="fonts"><i class="fa fa-bookmark-o"></i> bookmark-o</a>
	
	<a href="javascript:;" class="fonts"><i class="fa fa-calendar"></i> calendar</a>
	<a href="javascript:;" class="fonts"><i class="fa fa-calendar-o"></i> calendar-o</a>
	<a href="javascript:;" class="fonts"><i class="fa fa-calculator"></i> calculator</a>
	<a href="javascript:;" class="fonts"><i class="fa fa-bank"></i> bank</a>
	<a href="javascript:;" class="fonts"><i class="fa fa-barcode"></i> barcode</a>
	
	<a href="javascript:;" class="fonts"><i class="fa fa-book"></i> book</a>
	<a href="javascript:;" class="fonts"><i class="fa fa-bookmark"></i> bookmark</a>
	<a href="javascript:;" class="fonts"><i class="fa fa-bug"></i> bug</a>
	<a href="javascript:;" class="fonts"><i class="fa fa-cart-plus"></i> cart-plus</a>
	<a href="javascript:;" class="fonts"><i class="fa fa-child"></i> child</a>
	
	<a href="javascript:;" class="fonts"><i class="fa fa-check"></i> check</a>
	<a href="javascript:;" class="fonts"><i class="fa fa-check-circle"></i> check-circle</a>
	<a href="javascript:;" class="fonts"><i class="fa fa-check-circle-o"></i> check-circle-o</a>
	<a href="javascript:;" class="fonts"><i class="fa fa-check-square"></i> check-square</a>
	<a href="javascript:;" class="fonts"><i class="fa fa-check-square-o"></i> check-square-o</a>
	
	<a href="javascript:;" class="fonts"><i class="fa fa-clock-o"></i> clock-o</a>
	<a href="javascript:;" class="fonts"><i class="fa fa-tag"></i> tag</a>
	<a href="javascript:;" class="fonts"><i class="fa fa-bars"></i> bars</a>
	<a href="javascript:;" class="fonts"><i class="fa fa-close"></i> close</a>
	<a href="javascript:;" class="fonts"><i class="fa fa-dot-circle-o"></i> dot-circle-o</a>
	
	<a href="javascript:;" class="fonts"><i class="fa fa-cloud"></i> cloud</a>
	<a href="javascript:;" class="fonts"><i class="fa fa-cloud-download"></i> cloud-download</a>
	<a href="javascript:;" class="fonts"><i class="fa fa-cloud-upload"></i> cloud-upload</a>
	<a href="javascript:;" class="fonts"><i class="fa fa-cog"></i> cog</a>
	<a href="javascript:;" class="fonts"><i class="fa fa-cogs"></i> cogs</a>
	
	<a href="javascript:;" class="fonts"><i class="fa fa-comment"></i> comment</a>
	<a href="javascript:;" class="fonts"><i class="fa fa-comments"></i> comments</a>
	<a href="javascript:;" class="fonts"><i class="fa fa-comment-o"></i> comment-o</a>
	<a href="javascript:;" class="fonts"><i class="fa fa-comments-o"></i> comments-o</a>
	<a href="javascript:;" class="fonts"><i class="fa fa-map-marker"></i> map-marker</a>
	
	<a href="javascript:;" class="fonts"><i class="fa fa-print"></i> print</a>
	<a href="javascript:;" class="fonts"><i class="fa fa-copyright"></i> copyright</a>
	<a href="javascript:;" class="fonts"><i class="fa fa-crop"></i> crop</a>
	<a href="javascript:;" class="fonts"><i class="fa fa-edit"></i> edit</a>
	<a href="javascript:;" class="fonts"><i class="fa fa-envelope"></i> envelope</a>

	<a href="javascript:;" class="fonts"><i class="fa fa-ellipsis-h"></i> ellipsis-h</a>
	<a href="javascript:;" class="fonts"><i class="fa fa-ellipsis-v"></i> ellipsis-v</a>
	<a href="javascript:;" class="fonts"><i class="fa fa-exclamation"></i> exclamation</a>
	<a href="javascript:;" class="fonts"><i class="fa fa-exclamation-circle"></i> exclamation-circle</a>
	<a href="javascript:;" class="fonts"><i class="fa fa-warning"></i> warning</a>

	<a href="javascript:;" class="fonts"><i class="fa fa-exchange"></i> exchange</a>
	<a href="javascript:;" class="fonts"><i class="fa fa-external-link"></i> external-link</a>
	<a href="javascript:;" class="fonts"><i class="fa fa-filter"></i> filter</a>
	<a href="javascript:;" class="fonts"><i class="fa fa-flag"></i> flag</a>
	<a href="javascript:;" class="fonts"><i class="fa fa-flag-checkered"></i> flag-checkered</a>

	<a href="javascript:;" class="fonts"><i class="fa fa-folder"></i> folder</a>
	<a href="javascript:;" class="fonts"><i class="fa fa-folder-o"></i> folder-o</a>
	<a href="javascript:;" class="fonts"><i class="fa fa-folder-open"></i> folder-open</a>
	<a href="javascript:;" class="fonts"><i class="fa fa-folder-open-o"></i> folder-open-o</a>
	<a href="javascript:;" class="fonts"><i class="fa fa-gift"></i> gift</a>

	<a href="javascript:;" class="fonts"><i class="fa fa-globe"></i> globe</a>
	<a href="javascript:;" class="fonts"><i class="fa fa-group"></i> group</a>
	<a href="javascript:;" class="fonts"><i class="fa fa-history"></i> history</a>
	<a href="javascript:;" class="fonts"><i class="fa fa-image"></i> image</a>
	<a href="javascript:;" class="fonts"><i class="fa fa-inbox"></i> inbox</a>

	<a href="javascript:;" class="fonts"><i class="fa fa-leaf"></i> leaf</a>
	<a href="javascript:;" class="fonts"><i class="fa fa-language"></i> language</a>
	<a href="javascript:;" class="fonts"><i class="fa fa-keyboard-o"></i> keyboard-o</a>
	<a href="javascript:;" class="fonts"><i class="fa fa-magic"></i> magic</a>
	<a href="javascript:;" class="fonts"><i class="fa fa-location-arrow"></i> location-arrow</a>

	<a href="javascript:;" class="fonts"><i class="fa fa-level-down"></i> level-down</a>
	<a href="javascript:;" class="fonts"><i class="fa fa-level-up"></i> level-up</a>
	<a href="javascript:;" class="fonts"><i class="fa fa-mail-forward"></i> mail-forward</a>
	<a href="javascript:;" class="fonts"><i class="fa fa-mail-reply"></i> mail-reply</a>
	<a href="javascript:;" class="fonts"><i class="fa fa-mail-reply-all"></i> mail-reply-all</a>

	<a href="javascript:;" class="fonts"><i class="fa fa-download"></i> download</a>
	<a href="javascript:;" class="fonts"><i class="fa fa-upload"></i> upload</a>
	<a href="javascript:;" class="fonts"><i class="fa fa-trash"></i> trash</a>
	<a href="javascript:;" class="fonts"><i class="fa fa-trash-o"></i> trash-o</a>
	<a href="javascript:;" class="fonts"><i class="fa fa-spinner"></i> spinner</a>

	<a href="javascript:;" class="fonts"><i class="fa fa-money"></i> money</a>
	<a href="javascript:;" class="fonts"><i class="fa fa-refresh"></i> refresh</a>
	<a href="javascript:;" class="fonts"><i class="fa fa-music"></i> music</a>
	<a href="javascript:;" class="fonts"><i class="fa fa-paw"></i> paw</a>
	<a href="javascript:;" class="fonts"><i class="fa fa-pencil"></i> pencil</a>

	<a href="javascript:;" class="fonts"><i class="fa fa-plus"></i> plus</a>
	<a href="javascript:;" class="fonts"><i class="fa fa-minus"></i> minus</a>
	<a href="javascript:;" class="fonts"><i class="fa fa-plus-circle"></i> plus-circle</a>
	<a href="javascript:;" class="fonts"><i class="fa fa-minus-circle"></i> minus-circle</a>
	<a href="javascript:;" class="fonts"><i class="fa fa-plus-square"></i> plus-square</a>
	
	<a href="javascript:;" class="fonts"><i class="fa fa-toggle-on"></i> toggle-on</a>
	<a href="javascript:;" class="fonts"><i class="fa fa-toggle-off"></i> toggle-off</a>
	<a href="javascript:;" class="fonts"><i class="fa fa-square"></i> square</a>
	<a href="javascript:;" class="fonts"><i class="fa fa-square-o"></i> square-o</a>
	<a href="javascript:;" class="fonts"><i class="fa fa-sort"></i> sort</a>
	
	<a href="javascript:;" class="fonts"><i class="fa fa-star"></i> star</a>
	<a href="javascript:;" class="fonts"><i class="fa fa-star-o"></i> star-o</a>
	<a href="javascript:;" class="fonts"><i class="fa fa-star-half"></i> star-half</a>
	<a href="javascript:;" class="fonts"><i class="fa fa-star-half-o"></i> star-half-o</a>
	<a href="javascript:;" class="fonts"><i class="fa fa-shoping-cart"></i> shoping-cart</a>
	
	<a href="javascript:;" class="fonts"><i class="fa fa-share"></i> share</a>
	<a href="javascript:;" class="fonts"><i class="fa fa-share-alt"></i> share-alt</a>
	<a href="javascript:;" class="fonts"><i class="fa fa-share-square"></i> share-square</a>
	<a href="javascript:;" class="fonts"><i class="fa fa-share-square-o"></i> share-square-o</a>
	<a href="javascript:;" class="fonts"><i class="fa fa-signal"></i> signal</a>
	
	<a href="javascript:;" class="fonts"><i class="fa fa-sign-in"></i> sign-in</a>
	<a href="javascript:;" class="fonts"><i class="fa fa-sign-out"></i> sign-out</a>
	<a href="javascript:;" class="fonts"><i class="fa fa-sitemap"></i> sitemap</a>
	<a href="javascript:;" class="fonts"><i class="fa fa-sliders"></i> sliders</a>
	<a href="javascript:;" class="fonts"><i class="fa fa-smile-o"></i> smile-o</a>
	
	<a href="javascript:;" class="fonts"><i class="fa fa-search"></i> search</a>
	<a href="javascript:;" class="fonts"><i class="fa fa-search-minus"></i> search-minus</a>
	<a href="javascript:;" class="fonts"><i class="fa fa-search-plus"></i> search-plus</a>
	<a href="javascript:;" class="fonts"><i class="fa fa-send"></i> send</a>
	<a href="javascript:;" class="fonts"><i class="fa fa-send-o"></i> send-o</a>
	
	<a href="javascript:;" class="fonts"><i class="fa fa-server"></i> server</a>
	<a href="javascript:;" class="fonts"><i class="fa fa-random"></i> random</a>
	<a href="javascript:;" class="fonts"><i class="fa fa-recycle"></i> recycle</a>
	<a href="javascript:;" class="fonts"><i class="fa fa-registered"></i> registered</a>
	<a href="javascript:;" class="fonts"><i class="fa fa-qrcode"></i> qrcode</a>
	
	<a href="javascript:;" class="fonts"><i class="fa fa-question"></i> question</a>
	<a href="javascript:;" class="fonts"><i class="fa fa-question-circle"></i> question-circle</a>
	<a href="javascript:;" class="fonts"><i class="fa fa-quote-left"></i> quote-left</a>
	<a href="javascript:;" class="fonts"><i class="fa fa-quote-right"></i> quote-right</a>
	<a href="javascript:;" class="fonts"><i class="fa fa-th"></i> th</a>

	<a href="javascript:;" class="fonts"><i class="fa fa-th-large"></i> th-large</a>
	<a href="javascript:;" class="fonts"><i class="fa fa-barcode"></i> barcode</a>
	<a href="javascript:;" class="fonts"><i class="fa fa-eject"></i> eject</a>
	<a href="javascript:;" class="fonts"><i class="fa fa-chevron-left"></i> chevron-left</a>
	<a href="javascript:;" class="fonts"><i class="fa fa-chevron-right"></i> chevron-right</a>

	<a href="javascript:;" class="fonts"><i class="fa fa-chevron-up"></i> chevron-up</a>
	<a href="javascript:;" class="fonts"><i class="fa fa-chevron-down"></i> chevron-down</a>
	<a href="javascript:;" class="fonts"><i class="fa fa-asterisk"></i> asterisk</a>
	<a href="javascript:;" class="fonts"><i class="fa fa-link"></i> link</a>
	<a href="javascript:;" class="fonts"><i class="fa fa-tasks"></i> tasks</a>

	<a href="javascript:;" class="fonts"><i class="fa fa-paperclip"></i> paperclip</a>
	<a href="javascript:;" class="fonts"><i class="fa fa-list-ul"></i> list-ul</a>
	<a href="javascript:;" class="fonts"><i class="fa fa-table"></i> table</a>
	<a href="javascript:;" class="fonts"><i class="fa fa-columns"></i> columns</a>
	<a href="javascript:;" class="fonts"><i class="fa fa-clipboard"></i> clipboard</a>

	<a href="javascript:;" class="fonts"><i class="fa fa-caret-down"></i> caret-down</a>
	<a href="javascript:;" class="fonts"><i class="fa fa-caret-up"></i> caret-up</a>
	<a href="javascript:;" class="fonts"><i class="fa fa-caret-left"></i> caret-left</a>
	<a href="javascript:;" class="fonts"><i class="fa fa-caret-right"></i> caret-right</a>
	<a href="javascript:;" class="fonts"><i class="fa fa-angle-double-up"></i> angle-double-up</a>

	<a href="javascript:;" class="fonts"><i class="fa fa-angle-double-down"></i> angle-double-down</a>
	<a href="javascript:;" class="fonts"><i class="fa fa-angle-double-left"></i> angle-double-left</a>
	<a href="javascript:;" class="fonts"><i class="fa fa-angle-double-right"></i> angle-double-right</a>
	<a href="javascript:;" class="fonts"><i class="fa fa-angle-down"></i> angle-down</a>
	<a href="javascript:;" class="fonts"><i class="fa fa-angle-up"></i> angle-up</a>

	<a href="javascript:;" class="fonts"><i class="fa fa-angle-left"></i> angle-left</a>
	<a href="javascript:;" class="fonts"><i class="fa fa-angle-right"></i> angle-right</a>
	<a href="javascript:;" class="fonts"><i class="fa fa-compass"></i> compass</a>
	<a href="javascript:;" class="fonts"><i class="fa fa-windows"></i> windows</a>
	<a href="javascript:;" class="fonts"><i class="fa fa-cc-visa"></i> cc-visa</a>
	
</div>
