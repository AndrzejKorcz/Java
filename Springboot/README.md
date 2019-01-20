# Spring Boot

## G³ówne korzyœci jakie dostarcza nam Spring Boot to:

- £atwoœæ uruchomienia – paczka Spring Boot posiada wbudowany serwer i inne niezbêdne komponenty, które s¹ potrzebne do uruchomienia aplikacji.
- Automatyczna konfiguracja – do uruchomienia zasadniczej aplikacji nie jest wymagana ¿adna dodatkowa konfiguracja. Dodanie w³asnej jest opcjonalna dla osi¹gniêcia innych/nowych korzyœci.
- Szybkoœæ – tworzenie aplikacji z wykorzystaniem Spring Boot jest uproszczone co przek³ada siê szybszy i ³atwiejszy proces developmentu.

Istnieje gotowe narzêdzie, które pozwala na generowanie projektu Spring Boot:
https://start.spring.io/

Zasadnie pola jakie mo¿na wype³niæ:
Group – nazwa pakietu (widoczna w drzewie plików projektu).
Artefact – nazwa skompilowanego projektu.
Dependencies – zale¿noœci, czyli zewnêtrzne biblioteki rozszerzaj¹ce mo¿liwoœci podstawowej wersji Spring Boot.

Hello Word w Spring Boot
Aby stworzyæ pierwsz¹ prost¹ aplikacje „Hello World” wystarczy skorzystaæ w generatora, a jedyne co nale¿y zrobiæ to dodaæ zale¿noœæ Web w sekcji Dependencies.
Biblioteka ta umo¿liwia budowanie szeroko pojêtych aplikacji internetowych. Kiedy ten krok jest ju¿ wykonany wystarczy teraz pobraæ projekt i uruchomiæ go w œrodowisku programistycznym np. IntelliJ IDEA.
Dobr¹ praktyka tu¿ po zaimportowaniu projektu jest jego przebudowanie (operacja clean&build).

Po otworzeniu projektu dostêpna jest klasa uruchomieniowa.
```sh
package pl.hello; // Group (pakiet) zdefiniowany w generatorze + Artifact
  
 // wykorzystywane zale¿noœci
 import org.springframework.boot.SpringApplication;
 import org.springframework.boot.autoconfigure.SpringBootApplication;
  
 @SpringBootApplication // adnotacja wskazuj¹ca, ¿e jest to aplikacja Spring Boot
 public class HelloApplication {
  
     public static void main(String[] args) {
         SpringApplication.run(HelloApplication.class, args); // uruchomienie aplikacji
     }
 }
```
Czas aby napisaæ w³asna klasê wyœwietlaj¹ca napis Hello World!

W tym celu utwórz now¹ klasê wzoruj¹c siê na tym fragmencie kodu:
```sh
package pl.hello;
  
 import org.springframework.web.bind.annotation.RequestMapping;
 import org.springframework.web.bind.annotation.RestController;
  
 @RestController // rodzaj beana (typu klasy zarz¹dzanej przez Spring)
 public class HelloWorld { // nazwa klasy 
  
     @RequestMapping("/hello") // wskazanie pod jakim adresem dostêpna jest metoda
     public String hello() { // sygnatura metody
         return "Hello World! :)"; // zwracana wartoœæ przez przegl¹darkê 
     }
 }
```
W kodzie zosta³y wykorzystane dwie kluczowe adnotacje:

@RestController – wskazuj¹cy na to, ¿e zarz¹dzanym beanem jest kontroler (wiêcej wyjaœnieñ w kolejnej czêœci).
@RequestMapping – wskazuje pod jakim adresem dostêpna jest metoda. Poniewa¿ aplikacja to zostanie uruchomiana lokalnie tote¿ domyœlnie uruchamia siê pod adresem:
http://localhost:8080/

metoda wskazuje wartoœæ podana w adnotacji to /hello, dlatego wywo³anie metody powinno wyst¹piæ z localhost:8080/hello

adnotacje:
@Component – wspomniany na pocz¹tku najbardziej ogólny
@Repository – dedykowana dla klas, których zdaniem jest przechowywanie, agregowane danych
@Service – dedykowana klasom, które dostarczaj¹ us³ugi
@Controller/@RestController – dedykowany dla warstwy prezentacji lub/i dla API aplikacji

Aby w kodzie programu skorzystaæ z klasy która jest zarz¹dzania przez Spring nale¿y pole w klasie oznaczyæ adnotacj¹ (co jest widoczne w klasie Shop):

@Autowired – pozwala ona na wstrzykniêcie instancji klasy w oznaczone pole.
Tak oznakowane pole mo¿na ju¿ w pe³ni wykorzystywaæ – jest ona automatycznie inicjalizowana przez Spring. Spring poszukuje wszystkie klasy, które zosta³y opatrzone jedn¹ z adnotacji:

@Component
@Controller
@RestController
@Repository
@Service
A nastêpnie w przypadku odnalezienia, wykrzykuje jej instancje do pola oznaczonego adnotacj¹ @Autowired. Ten rodzaj wstrzykiwania nazywa siê wstrzykiwaniem do pól.

Mechanizm wstrzykiwania zale¿noœci jest znany nie tylko w Spring. Zasadniczo s¹ trzy rodzaje wstrzykiwania zale¿noœci. Spring pozwala na wykorzystywanie ich wszystkich. Dopuszczalne jest ich stosowanie przemienne. Mimo, ¿e jest to mo¿liwe, to dobre praktyki nie popieraj¹ takiemu dzia³aniu. Mo¿liwe jest wstrzykiwanie zale¿noœci poprzez:

Konstruktor
Pole
W³aœciwoœæ

Poni¿sza klasa jest przyk³adow¹, która bêdzie nastêpnie wstrzykiwana do klasy powi¹zanej.
```sh
@Component
public class ShoppingCard {
 
    private List<Product> productList;
 
    public ShoppingCard() {
        this.productList = new ArrayList<>();
    }
 
    public void addProductToShoppingCard(Product product)
    {
        productList.add(product);
    }
}
```
Wstrzykiwanie poprzez konstruktor
Wstrzykiwanie przez konstruktor jest najbardziej rekomendowanym sposobem wstrzykiwania zale¿noœci. Odbywa siê to poprzez napisaniem adnotacji @Autowired nad konstruktorem, oraz podaniem w parametrze wejœciowym klasê która przeznaczona jest do wstrzykniêcia. Ca³oœæ przedstawia kod poni¿ej.
```sh
@Component
public class Shop {
 
    private ShoppingCard shoppingCard;
 
    @Autowired
    public Shop(ShoppingCard shoppingCard) {
        this.shoppingCard = shoppingCard;
    }
 
    public void purchase() {
        shoppingCard.addProductToShoppingCard(new Product(1, "Milk"));
        shoppingCard.addProductToShoppingCard(new Product(2, "Tea"));
        shoppingCard.addProductToShoppingCard(new Product(3, "Bread"));
    }
}
```
Wstrzykiwanie poprzez pole
Jest to naj³atwiejszy sposób wstrzykiwania zale¿noœci. By³ on wykorzystywany w ramach tego kursu wielokrotnie. Do jego wykorzystania wystarczy nad polem umieœciæ adnotacje @Autowired. Sposób jest najszybszy i najprostszy do wykorzystania.
```sh
@Component
public class Shop {
     
    @Autowired
    private ShoppingCard shoppingCard;
 
    public void purchase() {
        shoppingCard.addProductToShoppingCard(new Product(1, "Milk"));
        shoppingCard.addProductToShoppingCard(new Product(2, "Tea"));
        shoppingCard.addProductToShoppingCard(new Product(3, "Bread"));
    }
}
```
Wstrzykiwanie poprzez w³aœciwoœæ
Sposób ten polega na utworzeniu w³aœciwoœci lub ¿argonowego setera ustawiaj¹cego wartoœæ pola, które przeznaczone jest do wstrzykniêcia. Nad nim musi znajdowaæ siê adnotacja @Autowired.
```sh
@Component
public class Shop {
     
    private ShoppingCard shoppingCard;
 
    @Autowired
    public void setShoppingCard(ShoppingCard shoppingCard) {
        this.shoppingCard = shoppingCard;
    }
     
    public void purchase() {
        shoppingCard.addProductToShoppingCard(new Product(1, "Milk"));
        shoppingCard.addProductToShoppingCard(new Product(2, "Tea"));
        shoppingCard.addProductToShoppingCard(new Product(3, "Bread"));
    }
}
```
Endpoint – W architekturze REST punkt koñcowy do którego mo¿na siê odwo³aæ. Czêstym przypadkiem jest odwo³ywanie siê do endpoint’a aby pobraæ dane.

Przyk³adem mo¿e byæ wywo³anie endponta stanowi¹cego adres:
http://<adresIpServera>:<portAplikacji>/<œcie¿kaWywo³ania>

Implementacja
Aby zaimplementowaæ to w Sping nale¿y nad klas¹, która ma t¹ funkcjonalnoœæ dostarczaæ dodaæ adnotacje @RestController, natomiast nad sam¹ metod¹ zwracaj¹c¹ dane adnotacje @RequestMapping oraz @ResponseBody.
```sh
@RestController
public class Shop {
 
    @RequestMapping("/getExample")
    @ResponseBody
    public String purchase() {
        return "example";
    }
}
```
Adnotacja @RestController zosta³a omówiona w drugiej czêœci tego kursu – Adnotacje i obiekty zarz¹dzane. Zaœ pozosta³e adnotacje to:

@RequestMapping – adnotacja wskazuj¹c¹, ¿e dana metoda stanowi Endpoint. Mo¿e byæ wywo³ywana zdalnie. Przyjmuje ona wartoœæ stanowi¹c¹ relatywn¹ œcie¿kê dla jej wywo³ania;
@ResponseBody – wskazówka dla kontekstu Spring, aby zawartoœæ metody (w tym przypadku String) by³ zwracany nie do modelu dla widoku lecz jako obiekt.
Teraz wywo³uj¹c metodê na lokalnej maszynie odwo³anie siê do metody jest nastêpuj¹ce:

http://localhost:8080/getExample

W pierwszej kolejnoœci adres maszyny + port aplikacyjny + wartoœæ przekazana w adnotacji @RequestMapping.
Mo¿liwoœci adnotacji @RequestMapping
Adnotacje @RequestMapping mo¿na równie¿ stosowaæ nad nazw¹ klasy. Wówczas odwo³anie do wszystkich metod w tej klasie bêdzie musia³o zostaæ poprzedzone wartoœci¹ w niej podanej. Poni¿szy przyk³ad prezentuje dodanie adnotacji do klasy.
```sh
@RestController
@RequestMapping("/api")
public class Shop {
 
    @RequestMapping("/getExample")
    @ResponseBody
    public String purchase() {
        return "example";
    }
}
```
Dla tego przypadku ka¿de wywo³anie metody z tej klasy musi zostaæ poprzedzone:
http://localhost:8080/api

Teraz, aby wywo³anie metody purchase() by³o mo¿liwe nale¿y odwo³aæ siê do adresu:
http://localhost:8080/api/getExample

Metody GET, HEAD, POST, PUT, PATCH, DELETE, OPTIONS, TRACE
Domyœlnie adnotacja @RequestMapping wskazuje, ¿e jest to metoda GET. Jednak podajac dodatkowy paramter mo¿na wskazac na dowoln¹ metodê HTTP.
```sh
@RestController
@RequestMapping("/api")
public class Shop {
 
    @RequestMapping(value = "/getExample", method = RequestMethod.POST)
    @ResponseBody
    public String purchase() {
        return "example";
    }
}
```


