/**!
 * easy-pie-chart
 * Lightweight plugin to render simple, animated and retina optimized pie charts
 *
 * @license 
 * @author Robert Fleischmann <rendro87@gmail.com> (http://robert-fleischmann.de)
 * @version 2.1.7
 **/

!function(a,b){"function"==typeof define&&define.amd?define([],function(){return a.EasyPieChart=b()}):"object"==typeof exports?module.exports=b():a.EasyPieChart=b()}(this,function(){var a=function(a,b){var c,d=document.createElement("canvas");a.appendChild(d),"object"==typeof G_vmlCanvasManager&&G_vmlCanvasManager.initElement(d);var e=d.getContext("2d");d.width=d.height=b.size;var f=1;window.devicePixelRatio>1&&(f=window.devicePixelRatio,d.style.width=d.style.height=[b.size,"px"].join(""),d.width=d.height=b.size*f,e.scale(f,f)),e.translate(b.size/2,b.size/2),e.rotate((-0.5+b.rotate/180)*Math.PI);var g=(b.size-b.lineWidth)/2;b.scaleColor&&b.scaleLength&&(g-=b.scaleLength+2),Date.now=Date.now||function(){return+new Date};var h=function(a,b,c){c=Math.min(Math.max(-1,c||0),1);var d=0>=c?!0:!1;e.beginPath(),e.arc(0,0,g,0,2*Math.PI*c,d),e.strokeStyle=a,e.lineWidth=b,e.stroke()},i=function(){var a,c;e.lineWidth=1,e.fillStyle=b.scaleColor,e.save();for(var d=24;d>0;--d)d%6===0?(c=b.scaleLength,a=0):(c=.6*b.scaleLength,a=b.scaleLength-c),e.fillRect(-b.size/2+a,0,c,1),e.rotate(Math.PI/12);e.restore()},j=function(){return window.requestAnimationFrame||window.webkitRequestAnimationFrame||window.mozRequestAnimationFrame||function(a){window.setTimeout(a,1e3/60)}}(),k=function(){b.scaleColor&&i(),b.trackColor&&h(b.trackColor,b.trackWidth||b.lineWidth,1)};this.getCanvas=function(){return d},this.getCtx=function(){return e},this.clear=function(){e.clearRect(b.size/-2,b.size/-2,b.size,b.size)},this.draw=function(a){b.scaleColor||b.trackColor?e.getImageData&&e.putImageData?c?e.putImageData(c,0,0):(k(),c=e.getImageData(0,0,b.size*f,b.size*f)):(this.clear(),k()):this.clear(),e.lineCap=b.lineCap;var d;d="function"==typeof b.barColor?b.barColor(a):b.barColor,h(d,b.lineWidth,a/100)}.bind(this),this.animate=function(a,c){var d=Date.now();b.onStart(a,c);var e=function(){var f=Math.min(Date.now()-d,b.animate.duration),g=b.easing(this,f,a,c-a,b.animate.duration);this.draw(g),b.onStep(a,c,g),f>=b.animate.duration?b.onStop(a,c):j(e)}.bind(this);j(e)}.bind(this)},b=function(b,c){var d={barColor:"#ef1e25",trackColor:"#f9f9f9",scaleColor:"#dfe0e0",scaleLength:5,lineCap:"round",lineWidth:3,trackWidth:void 0,size:110,rotate:0,animate:{duration:1e3,enabled:!0},easing:function(a,b,c,d,e){return b/=e/2,1>b?d/2*b*b+c:-d/2*(--b*(b-2)-1)+c},onStart:function(a,b){},onStep:function(a,b,c){},onStop:function(a,b){}};if("undefined"!=typeof a)d.renderer=a;else{if("undefined"==typeof SVGRenderer)throw new Error("Please load either the SVG- or the CanvasRenderer");d.renderer=SVGRenderer}var e={},f=0,g=function(){this.el=b,this.options=e;for(var a in d)d.hasOwnProperty(a)&&(e[a]=c&&"undefined"!=typeof c[a]?c[a]:d[a],"function"==typeof e[a]&&(e[a]=e[a].bind(this)));"string"==typeof e.easing&&"undefined"!=typeof jQuery&&jQuery.isFunction(jQuery.easing[e.easing])?e.easing=jQuery.easing[e.easing]:e.easing=d.easing,"number"==typeof e.animate&&(e.animate={duration:e.animate,enabled:!0}),"boolean"!=typeof e.animate||e.animate||(e.animate={duration:1e3,enabled:e.animate}),this.renderer=new e.renderer(b,e),this.renderer.draw(f),b.dataset&&b.dataset.percent?this.update(parseFloat(b.dataset.percent)):b.getAttribute&&b.getAttribute("data-percent")&&this.update(parseFloat(b.getAttribute("data-percent")))}.bind(this);this.update=function(a){return a=parseFloat(a),e.animate.enabled?this.renderer.animate(f,a):this.renderer.draw(a),f=a,this}.bind(this),this.disableAnimation=function(){return e.animate.enabled=!1,this},this.enableAnimation=function(){return e.animate.enabled=!0,this},g()};return b});

const kpis = [
    {
        'kpi': 'company-mood',
        'status': '#00ab03'
    },
    {
        'kpi': 'time-to-hire',
        'status': '#00ab03'
    },
    {
        'kpi': 'innovations',
        'status': '#F3BC14'
    },
    {
        'kpi': 'team-contribution',
        'status': '#00ab03'
    },
    {
        'kpi': 'cx-sentiment',
        'status': '#e53261'
    },
    {
        'kpi': 'uhd-rating',
        'status': '#00ab03'
    },
    {
        'kpi': 'platform-availability',
        'status': '#F3BC14'
    },
    {
        'kpi': 'loss-revenue',
        'status': '#e53261'
    },
    {
        'kpi': 'audit-tasks',
        'status': '#00ab03'
    },
    {
        'kpi': 'budget',
        'status': '#00ab03'
    },
    {
        'kpi': 'bug-fixes',
        'status': '#00ab03'
    },
    {
        'kpi': 'invoice-runtime',
        'status': '#F3BC14'
    },
    {
        'kpi': 'shareholders',
        'status': '#00ab03'
    },
    {
        'kpi': 'customers',
        'status': '#e53261'
    },
    {
        'kpi': 'operating-profit',
        'status': '#00ab03'
    },
    {
        'kpi': 'equity-ratio',
        'status': '#00ab03'
    },
    {
        'kpi': 'cam',
        'status': '#F3BC14'
    }
]

kpis.forEach(element => {
  var el = document.querySelector("." + element['kpi']);
  new EasyPieChart(el, {
    barColor: element['status'],
    lineWidth: 10,
    scaleColor: "#00378b",
    onStep: function(from, to, percent) {
      this.el.children[0].innerHTML = Math.round(percent);
    }
  });
});