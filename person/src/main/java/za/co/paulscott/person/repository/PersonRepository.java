package za.co.paulscott.person.repository;

/**
 * Created by pscot on 3/7/2016.
 */

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.orientechnologies.orient.core.sql.OCommandSQL;
import com.tinkerpop.blueprints.Edge;
import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.blueprints.impls.orient.OrientGraph;
import com.tinkerpop.blueprints.impls.orient.OrientGraphFactory;
import com.tinkerpop.blueprints.impls.orient.OrientVertexType;
import org.springframework.stereotype.Component;
import za.co.paulscott.person.model.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pscot on 3/2/2016.
 */

@Component
public class PersonRepository  {

    private OrientGraphFactory factory = new OrientGraphFactory("remote:localhost/iot", "root", "hello").setupPool(1, 10);

    public String addPerson(Person person) {
        OrientGraph graph = factory.getTx();
        try {
            OrientVertexType vertexType = graph.getVertexType("Person");
        } catch (NullPointerException e) {
            graph.createVertexType("Person");
        }
        OrientVertexType vertexType = graph.getVertexType("Person");
        Vertex p = graph.addVertex("class:Person");
        Object id = p.getId();

        p.setProperty("firstName", person.getFirstName());
        p.setProperty("lastName", person.getLastName());
        p.setProperty("email", person.getEmail());
        p.setProperty("age", person.getAge());
        p.setProperty("title", person.getTitle());
        p.setProperty("image", person.getImage());
        p.setProperty("homepage", person.getHomepage());

        // we use the knows array to create edges outgoing to other people
        //p.setProperty("knows", person.getKnows());

        graph.commit();
        graph.shutdown();
        return id.toString();
    }

    public List<Person> findByFirstName(String firstName) {
        List<Person> people = new ArrayList<Person>();
        OrientGraph graph = factory.getTx();
        for (Vertex v : graph.getVertices("firstName", firstName)) {
            Person p = null;
            p = personTranslator(v);
            people.add(p);

        }
        graph.shutdown();
        return people;
    }

    public List<Person> findByEmailAddress(String email) {
        List<Person> people = new ArrayList<Person>();
        OrientGraph graph = factory.getTx();
        for (Vertex v : graph.getVertices("email", email)) {
            Person p = null;
            p = personTranslator(v);
            people.add(p);

        }
        graph.shutdown();
        return people;
    }

    public List<Person> findByLastName(String lastName) {
        List<Person> people = new ArrayList<Person>();
        OrientGraph graph = factory.getTx();
        for (Vertex v : graph.getVertices("lastName", lastName)) {
            Person p = null;
            p = personTranslator(v);
            people.add(p);

        }
        graph.shutdown();
        return people;
    }

    public List<Person> findAll() {
        List<Person> people = new ArrayList<Person>();
        OrientGraph graph = factory.getTx();
        for (Vertex v : graph.getVertices()) {
            Person p = null;
            p = personTranslator(v);
            people.add(p);

        }
        graph.shutdown();
        return people;
    }

    public Person updatePerson(Person person) {
        OrientGraph graph = factory.getTx();
        String p = Jsonify(person);
        String sqlcmd = "UPDATE Person merge " + p + " WHERE @rid = " + person.getId();
        graph.getRawGraph().command(new OCommandSQL(sqlcmd)).execute();

        // return a copy of the new record
        return findByOId(person.getId());
    }

    private String Jsonify(Person person) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            String data = mapper.writeValueAsString(person);
            return data;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Person findByOId(String id) {
        OrientGraph graph = factory.getTx();
        Vertex v = graph.getVertex(id);
        if (v == null) {
            return null;
        }
        Person p = personTranslator(v);
        graph.shutdown();
        return p;
    }

    private Person personTranslator(Vertex v) {
        Person p = new Person();
        p.setAge(v.getProperty("age"));
        p.setEmail(v.getProperty("email"));
        p.setFirstName(v.getProperty("firstName"));
        p.setHomepage(v.getProperty("homepage"));
        p.setId(v.getId().toString());
        p.setImage(v.getProperty("image"));
        p.setKnows(v.getProperty("knows"));
        p.setLastName(v.getProperty("lastName"));
        p.setTitle(v.getProperty("title"));

        return p;
    }


    public String createKnows(Person p1, Person p2) {
        OrientGraph graph = factory.getTx();
        try {
            Vertex v1 = graph.getVertex(p1.getId());
            Vertex v2 = graph.getVertex(p2.getId());
            Edge knowsEdge = graph.addEdge(null, v1, v2, "knows");
            knowsEdge.setProperty("friendid", p2.getId());
            graph.commit();
            return "Successfully created relationship";

        } catch (Exception e) {
            graph.rollback();
            return "Failed to create relationship " + e.getMessage();
        } finally {
            graph.shutdown();
        }
    }

    public String breakKnows(Person p1, Person p2) {
        OrientGraph graph = factory.getTx();
        try {
            Vertex v1 = graph.getVertex(p1.getId());
            Vertex v2 = graph.getVertex(p2.getId());
            Iterable<Edge> edges = graph.getEdges();
            for (Edge e : edges) {
                if (v2.getId().equals(e.getProperty("friendid"))) {
                    graph.removeEdge(e);
                }
            }
            graph.commit();
            return "Successfully removed relationship";

        } catch (Exception e) {
            graph.rollback();
            return "Failed to remove relationship " + e.getMessage();
        } finally {
            graph.shutdown();
        }
    }

    public List<Person> listRelationships(Person p) {
        OrientGraph graph = factory.getTx();
        Iterable<Edge> edges = graph.getEdges();
        List<Person> rels = new ArrayList<Person>();
        for (Edge e : edges) {
            Object prop = e.getProperty("friendid");
            System.out.println(prop.toString());
            Person rp = findByOId(e.getProperty("friendid").toString());
            rels.add(rp);
        }
        return rels;
    }
}