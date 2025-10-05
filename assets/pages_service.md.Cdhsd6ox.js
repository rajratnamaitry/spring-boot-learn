import{_ as i,c as a,o as n,af as r}from"./chunks/framework.BPxE-czq.js";const v=JSON.parse('{"title":"Service Layer","description":"","frontmatter":{"outline":"deep"},"headers":[],"relativePath":"pages/service.md","filePath":"pages/service.md"}'),l={name:"pages/service.md"};function s(t,e,o,c,p,d){return n(),a("div",null,[...e[0]||(e[0]=[r(`<h1 id="service-layer" tabindex="-1">Service Layer <a class="header-anchor" href="#service-layer" aria-label="Permalink to “Service Layer”">​</a></h1><p>The service layer acts as an intermediary between controllers and data access objects (DAO), providing business logic and transaction management.</p><h2 id="service-implementation" tabindex="-1">Service Implementation <a class="header-anchor" href="#service-implementation" aria-label="Permalink to “Service Implementation”">​</a></h2><h3 id="_1-define-service-interface" tabindex="-1">1. Define Service Interface <a class="header-anchor" href="#_1-define-service-interface" aria-label="Permalink to “1. Define Service Interface”">​</a></h3><pre><code>\`\`\`java
// filepath: src/main/java/com/kart/example/myKart/service/EmployeeService.java
public interface EmployeeService {
    List&lt;EmployeeEntity&gt; findAll();
}
\`\`\`
</code></pre><h3 id="_2-implement-service-class" tabindex="-1">2. Implement Service Class <a class="header-anchor" href="#_2-implement-service-class" aria-label="Permalink to “2. Implement Service Class”">​</a></h3><pre><code>\`\`\`java
@Service
public class EmployeeServiceImp implements EmployeeService {
    private EmployeeDao employeeDao;

    @Autowired
    public EmployeeServiceImp(EmployeeDao theEmployeeDAO) {
        this.employeeDao = theEmployeeDAO;
    }

    @Override
    public List&lt;EmployeeEntity&gt; findAll() {
        return this.employeeDao.findAll();
    }
}
\`\`\`
</code></pre><h3 id="_3-use-service-in-controller" tabindex="-1">3. Use Service in Controller <a class="header-anchor" href="#_3-use-service-in-controller" aria-label="Permalink to “3. Use Service in Controller”">​</a></h3><pre><code>\`\`\`java
@RestController
@RequestMapping(&quot;/api/v2&quot;)
public class EmployeeServiceRestController {
    private EmployeeService employeeService;

    @Autowired
    public EmployeeServiceRestController(EmployeeService theEmployeeService) {
        this.employeeService = theEmployeeService;
    }
    
    @GetMapping(&quot;/employees&quot;)
    public List&lt;EmployeeEntity&gt; findAll() {
        return employeeService.findAll();
    }
}
\`\`\`
</code></pre><h2 id="key-concepts" tabindex="-1">Key Concepts <a class="header-anchor" href="#key-concepts" aria-label="Permalink to “Key Concepts”">​</a></h2><ol><li><p><strong>@Service Annotation</strong></p><ul><li>Marks class as service layer component</li><li>Enables component scanning</li><li>Allows autowiring</li></ul></li><li><p><strong>Constructor Injection</strong></p><ul><li>Recommended approach for dependency injection</li><li>Makes dependencies explicit</li><li>Supports final fields</li></ul></li><li><p><strong>Interface-based Design</strong></p><ul><li>Defines contract for service operations</li><li>Enables loose coupling</li><li>Facilitates testing and maintenance</li></ul></li></ol><div class="tip custom-block"><p class="custom-block-title">Best Practices</p><ul><li>Use constructor injection over field injection</li><li>Create service interfaces for better abstraction</li><li>Keep business logic in service layer, not in controllers</li><li>Use meaningful method names that reflect business operations</li></ul></div><h2 id="repository-vs-service-annotations" tabindex="-1">@Repository vs @Service Annotations <a class="header-anchor" href="#repository-vs-service-annotations" aria-label="Permalink to “@Repository vs @Service Annotations”">​</a></h2><p>Here&#39;s the key differences between these Spring stereotypes:</p><h2 id="repository" tabindex="-1">@Repository <a class="header-anchor" href="#repository" aria-label="Permalink to “@Repository”">​</a></h2><ul><li>Used for DAO/persistence layer</li><li>Marks the class as a database access component</li><li>Translates JDBC exceptions into Spring&#39;s DataAccessException</li><li>Typically works directly with database/data access code</li><li>Usually contains queries and database operations</li></ul><h2 id="service" tabindex="-1">@Service <a class="header-anchor" href="#service" aria-label="Permalink to “@Service”">​</a></h2><ul><li>Used for business logic layer</li><li>Marks the class as a service provider</li><li>Contains business rules and calculations</li><li>Coordinates multiple repositories</li><li>Acts as a facade over repositories</li><li>Does not handle exceptions translation</li></ul>`,18)])])}const h=i(l,[["render",s]]);export{v as __pageData,h as default};
