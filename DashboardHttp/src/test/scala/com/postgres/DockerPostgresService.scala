package com.postgres

import com.whisk.docker.{DockerContainer, DockerKit}
import scala.concurrent.duration._


trait DockerPostgresService extends DockerKit {
  val internalPort = 44444
  val externalPort = 5432
  val user = "user"
  val password = "safepassword"
  val database = "mydb"
  val dbUrl = s"jdbc:postgresql://localhost:${externalPort}/${database}?autoReconnect=true&useSSL=false"
  val driver = "org.postgresql.Driver"
  val dockerImage = "postgres:9.6.2"

  val postgresContainer = DockerContainer(dockerImage)
    .withPorts((externalPort, Some(internalPort)))
    .withEnv(s"POSTGRES_USER=${user}", s"POSTGRES_PASSWORD=${password}", s"POSTGRES_DB=${database}")
    .withReadyChecker(
      new PostgresReadyChecker(dbUrl, user, password, driver).looped(15, 1.second)
    )

  abstract override def dockerContainers: List[DockerContainer] = postgresContainer :: super.dockerContainers
}
