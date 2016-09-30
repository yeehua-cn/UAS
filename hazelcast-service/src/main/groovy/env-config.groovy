environments {
    // 开发环境
    development {
        versionSuffix = "-development"

        configInterface = '127.0.0.1'
        name = 'uas.org'
        password = 'uas_123456'
    }
    // 测试环境
    test {
        versionSuffix = "-test"

        configInterface = '192.168.18.*'
        name = 'uas.org'
        password = 'uas_123456'
    }
    // 生产环境
    production {
        versionSuffix = "-production"

        configInterface = '192.168.90.*'
        name = 'uas.org'
        password = 'uas_123456'
    }
}
