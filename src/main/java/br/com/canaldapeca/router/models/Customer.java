package br.com.canaldapeca.router.models;

import br.com.canaldapeca.router.RouterApplication;

import javax.persistence.*;
import java.util.Calendar;

/**
 * Entidade Customer, capaz de armazenar os diferentes IDs de sistemas
 */
@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true, unique = true, name = "csw_id" )
    private Long cwsId;

    @Column(nullable = true, unique = true, name = "salesforce_Id")
    private Long originId;

    @Column(nullable = true, unique = true, name = "origin_id" )
    private String salesForceId;

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar lastSyncDate = Calendar.getInstance();


    public static String getIntegrationColumn(RouterApplication.IntegrationType type){
        if( type == RouterApplication.IntegrationType.CWS ){
            return "cws_id" ;
        } else if( type == RouterApplication.IntegrationType.SALESFORCE ){
            return "salesforce_Id";
        } else {
            return "origin_id";
        }
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCwsId() {
        return cwsId;
    }

    public void setCwsId(Long cwsId) {
        this.cwsId = cwsId;
    }

    public Long getOriginId() {
        return originId;
    }

    public void setOriginId(Long originId) {
        this.originId = originId;
    }

    public String getSalesForceId() {
        return salesForceId;
    }

    public void setSalesForceId(String salesForceId) {
        this.salesForceId = salesForceId;
    }

    public Calendar getLastSyncDate() {
        return lastSyncDate;
    }

    public void setLastSyncDate(Calendar lastSyncDate) {
        this.lastSyncDate = lastSyncDate;
    }
}
