module.exports = {
    configureWebpack: {
        devtool: 'source-map'
    },
    devServer: {
        port: 3333, // 启动端口
        open: true  // 启动后是否自动打开网页
    }
}