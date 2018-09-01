// see http://vuejs-templates.github.io/webpack for documentation.
var path = require('path')

module.exports = {
	build: {
		env: require('./prod.env'),
		index: path.resolve(__dirname, '../dist/index.html'),
		assetsRoot: path.resolve(__dirname, '../dist'),
		assetsSubDirectory: 'static',
		assetsPublicPath: '/',
		productionSourceMap: false,
		// Gzip off by default as many popular static hosts such as
		// Surge or Netlify already gzip all static assets for you.
		// Before setting to `true`, make sure to:
		// npm install --save-dev compression-webpack-plugin
		productionGzip: false,
		productionGzipExtensions: ['js', 'css'],
		port: 9000,
			sellUrl: 'http://yuisamapre.s1.natapp.cc',
			openidUrl: 'http://yuisamasell.natapp1.cc/sell/wechat/authorize',
        	wechatPayUrl: 'http://yuisamapre.s1.natapp.cc'
	},
	dev: {
		env: require('./dev.env'),
		port: 8088,
		assetsSubDirectory: 'static',
		assetsPublicPath: '/',
		proxyTable: {
			'/sell':{
				target:'http://127.0.0.1:8080/',
				changeOrgin:true,
			}
		},
		// CSS Sourcemaps off by default because relative paths are "buggy"
		// with this option, according to the CSS-Loader README
		// (https://github.com/webpack/css-loader#sourcemaps)
		// In our experience, they generally work as expected,
		// just be aware of this issue when enabling this option.
		cssSourceMap: false,
		//本地8088映射地址,映射到阿里云服务器的8088端口# http://www.changyt.xyz:8088/
		sellUrl: 'http://www.changyt.xyz:8088',
		//本地8080映射地址
		openidUrl: 'http://eeyjzd.natappfree.cc/sell/wechat/authorize',
		wechatPayUrl: 'http://127.0.0.1'
	}
}












