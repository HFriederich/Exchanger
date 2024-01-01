package com.db.systel.exchanger.controller.client

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test


class ConfigurationClientTest {
    @Test
    fun writeConfigurationTest() {
        val configurationClient = ConfigurationClient()
        println(configurationClient.welcomeClient())
    }
}
