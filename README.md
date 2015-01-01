mina-dfs
========

A Distributed File Storage System Based on `Apache Mina <http://mina.apache.org/>`.

mina-dfs is aimed at providing a distributed, highly availability file system. it is total based on Java. 
while there will be several client implemented by other languange, such as C, python , NodeJS. every client can put file to the file system cluster.
and can fetch it with the returned URL.
 
This Project main targets for small-middle enterprise infrastructure, it contains following components:

Name Server
----------
Maintain a list of available data servers, all clients will contact with name server and one data server info will be returned to the clients. name server may or may not configured with HA capability.
other info may collected: disk usage, total file count, hit ratio, failed count in latest 10000 request, isFull

Data Server
----------
report it's status to name server, the possible reported data could be total disk capacity/disk usage/total file count/disk speed. Data server need to 
receive all file data from client.in the same time, if a backup server is configured with this data server, file data will also be transferred to the backup server for backup. and once file is store in data server locally, will report success, no matter whether the file is successfully stored in backup server.
data server also need to have a Apache configure with it to enable file access from the outside.

a data usually manage a list of disk partition. in each partition, there are three level of folder, Level 1--- 1000 folders, each folder can contain at least 10000 file

Backup Server
----------

connected with data server, backup server usually located on different machines than data server. backup server can serve more than one data servers for data protection.  
Client  (Java, C implementation)
---------
Client need to talk with name server, asking for data server info for file store. when file is transferred and successfully stored on data server
client will get a url throught which client can fetch the file data.
