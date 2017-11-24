#!/bin/bash
sqoop export --connect jdbc:oracle:thin:@host:1521/SERVICE_NAME.world \
--username <username> \
--password <pwd> \
--update-mode allowinsert \
--update-key <pk-column-name> \
--table ODS.PREDICTION_TABLE_NAME \
--export-dir /data_dev/OTB/inference-data-2017-2-10/part* \
--input-fields-terminated-by ',' \
--lines-terminated-by '\n' \
--columns "COLUMN1,COLUMN2,COLUMN-N" \
-m 4
