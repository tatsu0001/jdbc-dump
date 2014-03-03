import java.sql.{DriverManager, Connection, Statement}
import org.apache.tools.ant.{Project, NoBannerLogger}
import org.apache.tools.ant.taskdefs.SQLExec
import org.apache.tools.ant.types.resources.{StringResource, PropertyResource}

object JdbcDump {
  private val jdbcDriver = "org.sqlite.JDBC"
  private val jdbcUrl = "jdbc:sqlite:sample.db"

  def main(args: Array[String]): Unit = {
    val project = initProject

    val sql = new SQLExec
    val strRsc = new PropertyResource(project, "output")
    //val strRsc = new PropertyResource()

    sql.setProject(project)
    sql.setTaskName("sql")
    sql.setDriver(jdbcDriver)
    sql.setUrl(jdbcUrl)
    sql.setUserid("")
    sql.setPassword("")
    sql.setPrint(true)
    sql.setEscapeProcessing(false)
    sql.addText("SELECT * FROM TEST")
    sql.setOutput(strRsc)
    sql.setShowtrailers(false)

    sql.execute

    println("--")
    println(strRsc.getValue)
    println("--")
    //println(project.getProperty("output"))
  }

  def initProject(): Project = {
    val proj = new Project
    proj.init

    val logger = new NoBannerLogger
    logger.setMessageOutputLevel(Project.MSG_INFO)
    logger.setOutputPrintStream(System.out)
    logger.setErrorPrintStream(System.err)

    proj.addBuildListener(logger)
    proj
  }
}

