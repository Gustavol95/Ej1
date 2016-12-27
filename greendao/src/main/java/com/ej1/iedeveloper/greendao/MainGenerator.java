package com.ej1.iedeveloper.greendao;

import org.greenrobot.greendao.generator.DaoGenerator;
import org.greenrobot.greendao.generator.Entity;
import org.greenrobot.greendao.generator.Property;
import org.greenrobot.greendao.generator.Schema;

/**
 * Created by iedeveloper on 27/12/16.
 */

public class MainGenerator {
    private static final String PROJECT_DIR = System.getProperty("user.dir");


    public static void main(String[] args) {
        Schema schema = new Schema(2, "com.ej1.iedeveloper.db");
        schema.enableKeepSectionsByDefault();

        addTables(schema);

        try {
            new DaoGenerator().generateAll(schema, PROJECT_DIR + "/app/src/main/java");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void addTables(final Schema schema) {
        Entity usuario = agregarTablaUsuario(schema);
        Entity reporte = agregarTablaReporte(schema);

        Property idUsuario = reporte.addLongProperty("idUsuario").notNull().getProperty();
        usuario.addToMany(reporte, idUsuario, "reportes");
    }

    private static Entity agregarTablaUsuario(final Schema schema) {
        Entity user = schema.addEntity("Usuario");
        user.addIdProperty().primaryKey().autoincrement();
        user.addStringProperty("nombre").notNull();
        user.addStringProperty("token");

        return user;
    }

    private static Entity agregarTablaReporte(final Schema schema) {
        Entity repo = schema.addEntity("Reporte");
        repo.addIdProperty().primaryKey().autoincrement();
        repo.addStringProperty("nombre").notNull();
        repo.addStringProperty("autoriza");


        return repo;
    }
}
