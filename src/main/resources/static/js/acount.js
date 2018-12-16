/**
 * 选项卡
 */
$.fn.tabChance1 = function (options,callback) {
    var $menu = $(this);
    var timeAuto = "";
    options = $.extend({
        iconChange: false,
        menuContent: options.menuContent,
        auto: false,
        event:"mouseenter",
        dataName:false
    }, options);

    //设置触发事件
    $menu.children().on(options.event,function () {

        var $this=$(this);
        var dataVal = null;

        //回调函数切换
        if($.isFunction(callback)){
            dataVal =$this.attr(options.dataName);
            if ($this.hasClass('current')) {
                callback(dataVal);
                return false
            } else {
                change($this);
                callback(dataVal);
            }
        }else{
            //普通切换
            if ($this.hasClass('current')) {
                return false
            } else {
                change($this);
            }
        }
    });

    //切换方法
    function change(ele) {
        var index = ele.index();
        var index2 = index + 1;

        //切换导航栏选中元素
        ele.siblings().removeClass("current");
        ele.addClass("current");

        var aa = options.menuContent.children();
        aa.removeClass("current").eq(index).addClass("current");

        //切换icon状态
        if (options.iconChange) {
            $menu.find('li').find("img").each(function (i) {
                var index = i + 1;
                this.src = "img/" + "icon" + index + ".png"
            });
            ele.find("img").attr("src", "img/" + "icon" + index2 + "_active" + ".png")
        }

    }

    //自动切换
    if (options.auto) {
        auto();
        function auto() {
            timeAuto = setInterval(function () {
                var $eleCurrent = $menu.find('.current');
                var $next = $eleCurrent.next();

                var index = $eleCurrent.index();
                var maxIndex = $menu.children().last().index();

                if (index == maxIndex) {
                    change($menu.children().first())
                }
                else {
                    change($next)
                }
            }, options.auto)
        }

        $menu.find('a').hover(function () {
            clearInterval(timeAuto)
        }, function () {
            timeAuto = setInterval(function () {
                var $eleCurrent = $menu.find('.current');
                var $next = $eleCurrent.next();

                var index = $eleCurrent.index();
                var maxIndex = $menu.children().last().index();

                if (index == maxIndex) {
                    change($menu.children().first())
                }
                else {
                    change($next)
                }
            }, options.auto)
        });
    }
};



 /**
     * 导航鼠标跟随
     */
    $(function () {
        var $navcur = $(".nav_current");
        var $nav = $(".nav");
        var current = ".current";
        var itemW = $nav.find(current).innerWidth();	//默认当前位置宽度
		console.log(itemW);
        if ($nav.find(current).length > 0) {
            var defLeftW = $nav.find(current).position().left+20;	//默认当前位置Left值

        } else {
            return false
        }

        //添加默认下划线
        $navcur.animate({
            left: defLeftW,
            width: itemW
        }, 0);

        //hover事件
        $nav.find("li").hover(function () {
            var index = $(this).index();	//获取滑过元素索引值
            var leftW = $(this).position().left;	//获取滑过元素Left值
             var itemW = $nav.find(current).innerWidth();	//默认当前位置宽度
            //var currentW = $nav.find("li").eq(index).innerWidth();	//获取滑过元素Width值
            $navcur.stop().animate({
                left: leftW+20,
                width: itemW
            }, 300);

        }, function () {
            if ($(this).find("a").hasClass("current")) {
                return false
            } else {
                $navcur.stop().animate({
                    left: defLeftW,
                    width: itemW
                }, 300)
            }
        });

        //click事件
        $nav.find("a").on("click", function () {
            $nav.find("a").removeClass('current');
            $(this).addClass("current");
            itemW = $nav.find(current).innerWidth();	//更新当前位置宽度
            defLeftW = $nav.find(current).position().left+20;	//更新当前位置Left值
        })
    });
    



//	$(document).ready(function(){
//		var screenHeight = $(window).height();//获取屏幕可视区域的高度。
//		var divHeight = $(".footer").height();//bottomDiv的高度再加上它一像素的边框。
//		var scrollHeight = $(document).scrollTop();//获取滚动条滚动的高度
//		//console.log(screenHeight);
//			$(".footer").css("top",screenHeight+scrollHeight - divHeight);
//		$(window).scroll(function(){
//			var scrollHeight = $(document).scrollTop();//获取滚动条滚动的高度。
//			if(!window.XMLHttpRequest){
//				$(".footer").css("top",screenHeight + scrollHeight - divHeight);
//			}//判断是否为IE6，如果是，执行大括号中内容
//		})
//	})
