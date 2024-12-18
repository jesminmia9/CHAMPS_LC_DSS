package Common;

import java.util.List;

/**
 * Created by TanvirHossain on 28/11/2015.
 */
public class Table_Mapping_Server
{
    private String local_table;
    public void setLocalTable(String _local_table){this.local_table=_local_table;}
    public String getLocalTable(){return this.local_table;}

    private String server_table;
    public void setServerTable(String _server_table){this.server_table=_server_table;}
    public String getServerTable(){return this.server_table;}
}