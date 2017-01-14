/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hbrs.nosql.mongoweb.db;

/**
 *
 * @author Franz Dahmann
 */

import com.mongodb.AggregationOutput;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class MongoDBConnector
{
    MongoClient  mongoClient;
    DB           db;
    DBCollection coll;
    private List answer;
    
    public MongoDBConnector()
    {
        initDefaultConnect();
    }
    
    public DBCursor getAllDBObjectsFromCollection()
    {
        DBCursor curse = coll.find();
        return curse;
    }
    
    public String getConnectionInfo()
    {
        return mongoClient.getAddress().toString();
    }
    
    public String getCollectionName()
    {
        return coll.getName();
    }
    
    public String getDBName()
    {
        return db.getName();
    }
    
    public DBCursor getDBObjectsFromCollectionUsingFind( DBObject query )
    {
        DBCursor curse = coll.find( query );
        return curse;
    }
    
    public List< DBObject > getDistinctQueryTextSearch( String input, String outputColumn, String distinctColumn )
    {
        Pattern pat = Pattern.compile( input, Pattern.CASE_INSENSITIVE );
        DBObject query;
        query = new BasicDBObject( distinctColumn, pat );
        List< DBObject > answer = coll.distinct( outputColumn, query );
        return answer;
    }
    
    public long getDocsinCollection()
    {
        return coll.count();
    }
    
    public List getListFromCollectionUsingDistinct( String output, DBObject query )
    {
        List curse = coll.distinct( output, query );
        return curse;
    }
    
    public List< DBObject > getQuery1( String input )
    {
        Pattern pat = Pattern.compile( input, Pattern.CASE_INSENSITIVE );
        DBObject query;
        query = new BasicDBObject( "Thema_der_Arbeit", pat );
        List< DBObject > answer = coll.distinct( "1_Pruefer", query );
        return answer;
    }
    
    public List< DBObject > getQuery2()
    {
        DBObject nequery = new BasicDBObject( "$ne", "" );
        
        List< DBObject > obj = new ArrayList<>();
        obj.add( new BasicDBObject( "1_Pruefer", nequery ) );
        obj.add( new BasicDBObject( "2_Pruefer", nequery ) );
        obj.add( new BasicDBObject( "3_Pruefer", nequery ) );
        
        DBObject orquery = new BasicDBObject( "$or", obj );
        
        List< DBObject > answer = coll.distinct( "1_Pruefer", orquery );
        return answer;
    }
    
    public List< DBObject > getQuery3( String input )
    {
        Pattern pat = Pattern.compile( input, Pattern.CASE_INSENSITIVE );
        DBObject query;
        query = new BasicDBObject( "1_Pruefer", pat );
        List< DBObject > answer = coll.distinct( "Thema_der_Arbeit", query );
        return answer;
    }
    
    public int getQuery4( String input )
    {
        List< DBObject > obj = new ArrayList<>();
        obj.add( new BasicDBObject( "Semester", input ) );
        obj.add( new BasicDBObject( "Zeugnis", 1 ) );
        DBObject andQuery = new BasicDBObject( "$and", obj );
        int i = coll.find( andQuery ).count();
        return i;
    }
    
    public List< DBObject > getQuery5()
    {
        BasicDBObject nequery = new BasicDBObject( "$ne", "" );
        BasicDBObject query = new BasicDBObject( "Firma", nequery );
        List< DBObject > answer = coll.distinct( "Firma", query );
        return answer;
    }
    
    public AggregationOutput getQuery6()
    {
        List< DBObject > obj = new ArrayList<>();
        //Erstes Element der Queryliste
        Object sizeQuery = new BasicDBObject( "$size", 0 );
        DBObject neQuery = new BasicDBObject( "$not", sizeQuery );
        DBObject matchQuery = new BasicDBObject( "1_Pruefer", neQuery );
        obj.add( new BasicDBObject( "$match", matchQuery ) );
        //Zweites Element der Queryliste
        obj.add( new BasicDBObject( "$unwind", "$1_Pruefer" ) );
        //Drittes Element der Queryliste
        DBObject sumQuery = new BasicDBObject( "$sum", 1 );
        DBObject auswahlQuery = new BasicDBObject( "_id", "$1_Pruefer" ).append( "Anzahl", sumQuery );
        obj.add( new BasicDBObject( "$group", auswahlQuery ) );
        //Viertes Element der Queryliste
        DBObject anzahlQuery, greaterQuery;
        greaterQuery = new BasicDBObject( "$gte", 2 );
        anzahlQuery = new BasicDBObject( "Anzahl", greaterQuery );
        obj.add( new BasicDBObject( "$match", anzahlQuery ) );
        //Fünftes Element der Queryliste
        DBObject sortQuery = new BasicDBObject( "Anzahl", -1 );
        obj.add( new BasicDBObject( "$sort", sortQuery ) );
        //Sechstes Element der Queryliste
        obj.add( new BasicDBObject( "$limit", 200 ) );
        AggregationOutput answer = coll.aggregate( obj );
        return answer;
    }
    
    public List< DBObject > getQuery7( String input )
    {
        Pattern pat = Pattern.compile( input, Pattern.CASE_INSENSITIVE );
        DBObject query;
        query = new BasicDBObject( "Thema_der_Arbeit", pat );
        List< DBObject > answer = coll.distinct( "Thema_der_Arbeit", query );
        return answer;
    }
    
    public AggregationOutput getQuery8( String input )
    {
        List< DBObject > obj = new ArrayList<>();
        Pattern pat = Pattern.compile( input, Pattern.CASE_INSENSITIVE );
        DBObject query;
        query = new BasicDBObject( "Thema_der_Arbeit", pat );
        BasicDBObject mQuery = new BasicDBObject( "$match", query );
        obj.add( mQuery );
        DBObject sizeQuery = new BasicDBObject( "$size", 0 );
        DBObject neQuery = new BasicDBObject( "$not", sizeQuery );
        DBObject matchQuery = new BasicDBObject( "1_Pruefer", neQuery );
        obj.add( new BasicDBObject( "$match", matchQuery ) );
        obj.add( new BasicDBObject( "$unwind", "$1_Pruefer" ) );
        DBObject sumQuery = new BasicDBObject( "$sum", 1 );
        DBObject auswahlQuery = new BasicDBObject( "_id", "$1_Pruefer" ).append( "Anzahl", sumQuery );
        obj.add( new BasicDBObject( "$group", auswahlQuery ) );
        DBObject sortQuery = new BasicDBObject( "Anzahl", -1 );
        obj.add( new BasicDBObject( "$sort", sortQuery ) );
        AggregationOutput answer = coll.aggregate( obj );
        return answer;
    }
    
    public void initDefaultConnect()
    {
        mongoClient = new MongoClient( "10.20.110.43", 27021 );
        db = mongoClient.getDB( "abDB" );
        coll = db.getCollection( "abDBsimpleCSV" );
    }
    
    public void insertNewDocumnet( String dokument )
    {
        BasicDBObject object = new BasicDBObject( "newKey", dokument );
        insertNewDocumnet( object );
    }
    
    public void insertNewDocumnet( DBObject dokument )
    {
        coll.insert( dokument );
    }
    
    public void setnewServerConnection( String s, int i )
    {
        mongoClient = new MongoClient( s, i );
    }
    
    public void setnewDBConnection( String s )
    {
        db = mongoClient.getDB( s );
    }
    
    public void setnewCollectionConnection( String s )
    {
        coll = db.getCollection( s );
    }
    
    public void updateDocument( DBObject query, DBObject update )
    {
        coll.update( query, update, false, false );
    }
    
    
}
