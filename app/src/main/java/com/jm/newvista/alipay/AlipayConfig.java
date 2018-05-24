package com.jm.newvista.alipay;

public class AlipayConfig {
    /**
     * 支付宝支付业务：入参app_id
     */
    public static final String APPID = "2016091400507922";

    /**
     * Seller's ID
     * 支付宝账户登录授权业务：入参pid值
     */
    public static final String PID = "2088102175565295";
    /**
     * Seller's account
     * 支付宝账户登录授权业务：入参target_id值
     */
    public static final String TARGET_ID = "yuqsmp1884@sandbox.com";

    /** 商户私钥，pkcs8格式 */
    /** 如下私钥，RSA2_PRIVATE 或者 RSA_PRIVATE 只需要填入一个 */
    /** 如果商户两个都设置了，优先使用 RSA2_PRIVATE */
    /** RSA2_PRIVATE 可以保证商户交易在更加安全的环境下进行，建议使用 RSA2_PRIVATE */
    /** 获取 RSA2_PRIVATE，建议使用支付宝提供的公私钥生成工具生成， */
    /**
     * 工具地址：https://doc.open.alipay.com/docs/doc.htm?treeId=291&articleId=106097&docType=1
     */
    public static final String RSA2_PRIVATE = "MIIEuwIBADANBgkqhkiG9w0BAQEFAASCBKUwggShAgEAAoIBAQCI6xAoWu6R9Paz+bFP" +
            "+/PphBAp7lxQ2XEEGYvRm66dm1trYNUB5hoq8" +
            "/1CGuMivoypSbshSvxQcCiUTWp5pujeiNoJTepeS8QuJg5I4LsBwYwVuAeajQy26hCo9EzGY9x" +
            "+EBwxHLYivZa6gkPqehJSmjSSeDBlmDz1O5Xk+jXmkt9srbwtV/wd+Uo" +
            "/4BVllf3iQ1bzYyYQvFsYGh6PPT3WSktSBQY8QsGxO9omCA55H2dHDuczrEWIs3RCQEqKIuCcfeYjkRDAY6B3JIxeY3BGaKqi9ejBjO7l2LhF7d2TmkRzG5DcpUUPm6alhNDkZScvUrbPzWXhJbH4clRagaDjAgMBAAECgf9lnhBf4x0tNqHi8W6n7lcY+OsGM+JQHxHB+TPMXb87OhKfnz54Kk9BkdRlO49YOhQrkI1SKFUbXiK/7rUXlMlAYFFylmFbmt//YD8515DzvLI7bFo4WncVSqMSwj44/+Slq09BJA4JuqwjwIqne/IdXFWoX4uL23FikNavGwTmGwPgUupTDlPn7hfPZCamZTdQHs3GNIIgULqb7tGQhwUIAJsqFk1WSXzkv3609jD63Pv8nr4llh25LlR9e/gTsqRjR4ntFMmfc/2KDdCenHe9raPCqPjGosgBv1Eu8RjlLWMajrxB+IlUzd/+vNn3fbdMnTRtSa52pZRw2Ft85IECgYEA0quYOHIP/uVcmzBQXIj4Bsbgl0qZmtntAyD4BE6Wq96J4vyMurV8h5MwWsfvLrUJvSvuuLv3bB2IuEq5hDQcAjW8/z0gTXp8W2A03+QJgNt+DTawsTTDyq84QL5O8LF49DpYqif9VG1eeonjdYQWZOrERJepdaK3Nux+a5FPfuECgYEApmD2zk+HyLmcr2kuw5XRWIKuC0EHJF4fCJKJRgujFprA5WoYANmEcR26IclFeXHSWB5ae5Tnse12HWumfitcIaeIaVFlBbFeXAFRje2mnhngq6cmlqAtcX6sKCDfOI45L4GYAI36aCR5hqbuXR8sJ5pX300I2FOMEHyPIO087EMCgYEAyFhhkcR/i8Op9vfSZL3pSeCWM6rBO29mI+SVmNr4FQi0jsXoO6Negzwp7xtWTk/irCF8i/hNy2am38RIev5naH6cdws1jhke7HMotYcStpWGYaCSFmP8kuNhlsilH+dXBMP8EA2psEG2aMh7JQXxmhVdg3HLuVB99mCftUJinGECgYBpkRaoex/txY/cYMHSScol/ezOqTkN40aPmuLM9e9KFXEVX4bRazK0zybd7yZM02KilMfWp/mHFOOf8UEXxw1TMUjFgma2AP+dIx0LD+JraIH8iDdc1U9gNv6nC4x3Bw7TzMeUeoGK+LG/NUYDAhdzHWRqBCf7kq9Jeq8aNk00nwKBgDhKCV4++paD0kjhel1ypWOWEbwv9XNaxmPWTJA/ycsHgNCk/PoTktx2YMqc9eVCzspUlE1AGEo8GA/DBCWILNM3D4niyLu2jy5w24ibqzsH0deSVnV6SeNQYdKP9jV3ZK3crcd4objC6JPuakUkuMBj3l/RWO3eoUxqZBJ+lxS5";
    public static final String RSA_PRIVATE = "";

    public static final int SDK_PAY_FLAG = 1;
    public static final int SDK_AUTH_FLAG = 2;

}
