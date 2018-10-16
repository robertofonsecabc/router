package br.com.canaldapeca.router.models;

import br.com.canaldapeca.router.RouterApplication;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Objeto que representa todos os requests
 */
public class RequestModel {

    // Sistema que originou a chamada
    private RouterApplication.IntegrationSystem origin;

    // Tipo da Entidade
    private RouterApplication.IntegrationType entity;

    // Id da entidade
    @NotNull
    @NotEmpty
    private String id;

    public RouterApplication.IntegrationSystem getOrigin() {
        return origin;
    }

    public void setOrigin(RouterApplication.IntegrationSystem origin) {
        this.origin = origin;
    }

    public RouterApplication.IntegrationType getEntity() {
        return entity;
    }

    public void setEntity(RouterApplication.IntegrationType entity) {
        this.entity = entity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String toJson() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(this);
        System.out.println(json);
        return json;
    }
}
