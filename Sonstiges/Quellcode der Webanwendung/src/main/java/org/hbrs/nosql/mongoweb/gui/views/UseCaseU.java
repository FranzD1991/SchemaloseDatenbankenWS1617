/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hbrs.nosql.mongoweb.gui.views;

import com.vaadin.ui.*;
import org.hbrs.nosql.mongoweb.db.MongoDBConnector;
import org.hbrs.nosql.mongoweb.model.UseCase;

/**
 * @author Franz
 */
public class UseCaseU extends AbstractUseCase
{
    @Override
    protected void setUp()
    {
        MongoDBConnector mongo = new MongoDBConnector();
        
        VerticalLayout vl = new VerticalLayout();
        
        //CRUD Operations
        Label lbCreate = new Label( "Update:" );
        addComponent( lbCreate );
        
        HorizontalLayout hlCreate = new HorizontalLayout();
        TextField tfCreate = new TextField( "Neues Dokument:" );
        hlCreate.addComponent( tfCreate );
        Button btSaveCreate = new Button( "Speichern" );
        btSaveCreate.addClickListener( new Button.ClickListener()
        {
            @Override
            public void buttonClick( Button.ClickEvent clickEvent )
            {
                if ( !tfCreate.getValue().equals( "" ) )
                {
                    String doc = tfCreate.getValue();
                    mongo.insertNewDocumnet( doc );
                }
            }
        } );
        
        hlCreate.addComponent( btSaveCreate );
        
        addComponent( hlCreate );
        
        //Read: All
        
        //Update:
        // https://www.mkyong.com/mongodb/java-mongodb-update-document/
        // http://mongodb.github.io/mongo-java-driver/3.4/driver/getting-started/quick-start/
        
        //Delete: Attributes or Documents
        
        setMargin( true );
        setSpacing( true );
    }
    
    @Override
    protected UseCase getUseCase()
    {
        return ( UseCase.UCU );
    }
}
