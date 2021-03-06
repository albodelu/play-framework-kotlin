package utils

import developers.storage.DeveloperEntity
import io.ebean.Ebean
import io.ebean.EbeanServerFactory
import io.ebean.config.ServerConfig
import org.avaje.datasource.DataSourceConfig
import org.junit.Before
import play.test.WithApplication

open class ApplicationWithDatabase : WithApplication() {

  @Before
  fun createDatabase() {
    configureEbean()
  }

  private fun configureEbean() {
    val dataSourceConfig = DataSourceConfig()
    dataSourceConfig.username = "root"
    dataSourceConfig.password = "root"
    dataSourceConfig.driver = "org.h2.Driver"
    dataSourceConfig.url = "jdbc:h2:mem:play;MODE=MYSQL;DB_CLOSE_DELAY=-1;DATABASE_TO_UPPER=FALSE"
    val config = ServerConfig()
    config.dataSourceConfig = dataSourceConfig
    config.name = "default"
    config.isDdlGenerate = true
    config.isDdlRun = true
    config.classes = listOf(DeveloperEntity::class.java)
    Ebean.register(EbeanServerFactory.create(config), true)
  }
}