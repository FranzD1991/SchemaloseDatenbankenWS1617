/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hbrs.nosql.mongoweb.gui.views;

import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import java.time.Duration;
import java.time.LocalTime;
import java.util.List;

import org.hbrs.nosql.mongoweb.db.MongoDBConnector;
import org.hbrs.nosql.mongoweb.model.UseCase;

/**
 * @author Franz
 */
public class UseCase4 extends AbstractUseCase
{
    @Override
    protected void setUp()
    {
        //Add your Vaadin Coding here ;)
        //MongoDB Connector
        MongoDBConnector mongo = new MongoDBConnector();
        
        VerticalLayout uc4 = new VerticalLayout();
        
        TextField uc4input = new TextField();
        
        uc4input.setCaption( "Geben Sie das Semester ein für das Sie die Anzahl der Abschlüsse wollen!" );
        
        Button button = new Button( "Suche" );
        
        button.addClickListener( e ->
                                 {
                                     uc4.removeAllComponents();
            
                                     LocalTime t1 = LocalTime.now();
            
                                     // load list with values
                                     int uc4Answer = mongo.getQuery4( uc4input.getValue() );
            
                                     LocalTime t2 = LocalTime.now();
                                     Duration elapsed = Duration.between( t1, t2 );
            
                                     String uc4Answ = uc4Answer + "";
                                     uc4.addComponent( new Label( uc4Answ ) );
            
                                     uc4.addComponent( new Label( "Time: " + elapsed ) );
            
                                     addComponent( uc4 );
                                 } );
        addComponents( uc4input, button );
        setMargin( true );
        setSpacing( true );
    }
    
    @Override
    protected UseCase getUseCase()
    {
        return ( UseCase.UC4 );
    }
}
