package org.hbrs.nosql.mongoweb.model;

import org.hbrs.nosql.mongoweb.gui.views.*;

/**
 * Created by Jan Eric Müller (aka Saso) on 20.12.2016.
 */
public enum UseCase
{
    UC1( "Use Case 1", UseCase1.class ),
    UC2( "Use Case 2", UseCase2.class ),
    UC3( "Use Case 3", UseCase3.class ),
    UC4( "Use Case 4", UseCase4.class ),
    UC5( "Use Case 5", UseCase5.class ),
    UC6( "Use Case 6", UseCase6.class ),
    UC7( "Use Case 7", UseCase7.class ),
    UC8( "Use Case 8", UseCase8.class ),
    UCC( "CRUD-Operationen: Create", UseCaseC.class ),
    UCR( "CRUD-Operationen: Read", UseCaseR.class ),
    UCU( "CRUD-Operationen: Update", UseCaseU.class ),
    UCD( "CRUD-Operationen: Delete", UseCaseD.class ),;
    
    private String name;
    private Class  view;
    
    UseCase( String name, Class view )
    {
        this.name = name;
        this.view = view;
    }
    
    public String getName()
    {
        return ( name );
    }
    
    public Class getView()
    {
        return view;
    }
}
