environments {
    // 开发环境
    development {
        versionSuffix = "-development"

        configInterface = '192.168.26.*'
    }
    // 测试环境
    test {
        versionSuffix = "-test"

        configInterface = '192.168.18.*'
    }
    // 生产环境
    production {
        versionSuffix = "-production"

        configInterface = '192.168.90.*'
    }
}
