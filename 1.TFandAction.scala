import org.apache.spark.sql.functions._
import org.apache.spark.sql.types._

val initialDF = spark
  .read
  .option("inferSchema", "true")
  .parquet("/mnt/wikipedia-readonly/pagecounts/staging_parquet_en_only/")
  .cache()

initialDF.count() // materialize the cache

val someDF = initialDF
  .withColumn("first", upper($"article".substr(0,1)) )
  .where( $"first".isin("A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z") )
  .groupBy($"project", $"first").sum()
  .drop("sum(bytes_served)")
  .orderBy($"first", $"project")
  .select($"first", $"project", $"sum(requests)".as("total"))
  .filter($"total" > 10000)

val total = someDF.count().toInt

someDF.take(total)

display( spark.emptyDataFrame )
