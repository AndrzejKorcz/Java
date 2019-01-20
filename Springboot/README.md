# Spring Boot

## G��wne korzy�ci jakie dostarcza nam Spring Boot to:

- �atwo�� uruchomienia � paczka Spring Boot posiada wbudowany serwer i inne niezb�dne komponenty, kt�re s� potrzebne do uruchomienia aplikacji.
- Automatyczna konfiguracja � do uruchomienia zasadniczej aplikacji nie jest wymagana �adna dodatkowa konfiguracja. Dodanie w�asnej jest opcjonalna dla osi�gni�cia innych/nowych korzy�ci.
- Szybko�� � tworzenie aplikacji z wykorzystaniem Spring Boot jest uproszczone co przek�ada si� szybszy i �atwiejszy proces developmentu.

Istnieje gotowe narz�dzie, kt�re pozwala na generowanie projektu Spring Boot:
https://start.spring.io/

Zasadnie pola jakie mo�na wype�ni�:
Group � nazwa pakietu (widoczna w drzewie plik�w projektu).
Artefact � nazwa skompilowanego projektu.
Dependencies � zale�no�ci, czyli zewn�trzne biblioteki rozszerzaj�ce mo�liwo�ci podstawowej wersji Spring Boot.

Hello Word w Spring Boot
Aby stworzy� pierwsz� prost� aplikacje �Hello World� wystarczy skorzysta� w generatora, a jedyne co nale�y zrobi� to doda� zale�no�� Web w sekcji Dependencies.
Biblioteka ta umo�liwia budowanie szeroko poj�tych aplikacji internetowych. Kiedy ten krok jest ju� wykonany wystarczy teraz pobra� projekt i uruchomi� go w �rodowisku programistycznym np. IntelliJ IDEA.
Dobr� praktyka tu� po zaimportowaniu projektu jest jego przebudowanie (operacja clean&build).

Po otworzeniu projektu dost�pna jest klasa uruchomieniowa.
```sh
package pl.hello; // Group (pakiet) zdefiniowany w generatorze + Artifact
  
 // wykorzystywane zale�no�ci
 import org.springframework.boot.SpringApplication;
 import org.springframework.boot.autoconfigure.SpringBootApplication;
  
 @SpringBootApplication // adnotacja wskazuj�ca, �e jest to aplikacja Spring Boot
 public class HelloApplication {
  
     public static void main(String[] args) {
         SpringApplication.run(HelloApplication.class, args); // uruchomienie aplikacji
     }
 }
```
Czas aby napisa� w�asna klas� wy�wietlaj�ca napis Hello World!

W tym celu utw�rz now� klas� wzoruj�c si� na tym fragmencie kodu:
```sh
package pl.hello;
  
 import org.springframework.web.bind.annotation.RequestMapping;
 import org.springframework.web.bind.annotation.RestController;
  
 @RestController // rodzaj beana (typu klasy zarz�dzanej przez Spring)
 public class HelloWorld { // nazwa klasy 
  
     @RequestMapping("/hello") // wskazanie pod jakim adresem dost�pna jest metoda
     public String hello() { // sygnatura metody
         return "Hello World! :)"; // zwracana warto�� przez przegl�dark� 
     }
 }
```
W kodzie zosta�y wykorzystane dwie kluczowe adnotacje:

@RestController � wskazuj�cy na to, �e zarz�dzanym beanem jest kontroler (wi�cej wyja�nie� w kolejnej cz�ci).
@RequestMapping � wskazuje pod jakim adresem dost�pna jest metoda. Poniewa� aplikacja to zostanie uruchomiana lokalnie tote� domy�lnie uruchamia si� pod adresem:
http://localhost:8080/

metoda wskazuje warto�� podana w adnotacji to /hello, dlatego wywo�anie metody powinno wyst�pi� z localhost:8080/hello

adnotacje:
@Component � wspomniany na pocz�tku najbardziej og�lny
@Repository � dedykowana dla klas, kt�rych zdaniem jest przechowywanie, agregowane danych
@Service � dedykowana klasom, kt�re dostarczaj� us�ugi
@Controller/@RestController � dedykowany dla warstwy prezentacji lub/i dla API aplikacji

Aby w kodzie programu skorzysta� z klasy kt�ra jest zarz�dzania przez Spring nale�y pole w klasie oznaczy� adnotacj� (co jest widoczne w klasie Shop):

@Autowired � pozwala ona na wstrzykni�cie instancji klasy w oznaczone pole.
Tak oznakowane pole mo�na ju� w pe�ni wykorzystywa� � jest ona automatycznie inicjalizowana przez Spring. Spring poszukuje wszystkie klasy, kt�re zosta�y opatrzone jedn� z adnotacji:

@Component
@Controller
@RestController
@Repository
@Service
A nast�pnie w przypadku odnalezienia, wykrzykuje jej instancje do pola oznaczonego adnotacj� @Autowired. Ten rodzaj wstrzykiwania nazywa si� wstrzykiwaniem do p�l.

Mechanizm wstrzykiwania zale�no�ci jest znany nie tylko w Spring. Zasadniczo s� trzy rodzaje wstrzykiwania zale�no�ci. Spring pozwala na wykorzystywanie ich wszystkich. Dopuszczalne jest ich stosowanie przemienne. Mimo, �e jest to mo�liwe, to dobre praktyki nie popieraj� takiemu dzia�aniu. Mo�liwe jest wstrzykiwanie zale�no�ci poprzez:

Konstruktor
Pole
W�a�ciwo��

Poni�sza klasa jest przyk�adow�, kt�ra b�dzie nast�pnie wstrzykiwana do klasy powi�zanej.
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
Wstrzykiwanie przez konstruktor jest najbardziej rekomendowanym sposobem wstrzykiwania zale�no�ci. Odbywa si� to poprzez napisaniem adnotacji @Autowired nad konstruktorem, oraz podaniem w parametrze wej�ciowym klas� kt�ra przeznaczona jest do wstrzykni�cia. Ca�o�� przedstawia kod poni�ej.
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
Jest to naj�atwiejszy spos�b wstrzykiwania zale�no�ci. By� on wykorzystywany w ramach tego kursu wielokrotnie. Do jego wykorzystania wystarczy nad polem umie�ci� adnotacje @Autowired. Spos�b jest najszybszy i najprostszy do wykorzystania.
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
Wstrzykiwanie poprzez w�a�ciwo��
Spos�b ten polega na utworzeniu w�a�ciwo�ci lub �argonowego setera ustawiaj�cego warto�� pola, kt�re przeznaczone jest do wstrzykni�cia. Nad nim musi znajdowa� si� adnotacja @Autowired.
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
Endpoint � W architekturze REST punkt ko�cowy do kt�rego mo�na si� odwo�a�. Cz�stym przypadkiem jest odwo�ywanie si� do endpoint�a aby pobra� dane.

Przyk�adem mo�e by� wywo�anie endponta stanowi�cego adres:
http://<adresIpServera>:<portAplikacji>/<�cie�kaWywo�ania>

Implementacja
Aby zaimplementowa� to w Sping nale�y nad klas�, kt�ra ma t� funkcjonalno�� dostarcza� doda� adnotacje @RestController, natomiast nad sam� metod� zwracaj�c� dane adnotacje @RequestMapping oraz @ResponseBody.
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
Adnotacja @RestController zosta�a om�wiona w drugiej cz�ci tego kursu � Adnotacje i obiekty zarz�dzane. Za� pozosta�e adnotacje to:

@RequestMapping � adnotacja wskazuj�c�, �e dana metoda stanowi Endpoint. Mo�e by� wywo�ywana zdalnie. Przyjmuje ona warto�� stanowi�c� relatywn� �cie�k� dla jej wywo�ania;
@ResponseBody � wskaz�wka dla kontekstu Spring, aby zawarto�� metody (w tym przypadku String) by� zwracany nie do modelu dla widoku lecz jako obiekt.
Teraz wywo�uj�c metod� na lokalnej maszynie odwo�anie si� do metody jest nast�puj�ce:

http://localhost:8080/getExample

W pierwszej kolejno�ci adres maszyny + port aplikacyjny + warto�� przekazana w adnotacji @RequestMapping.
Mo�liwo�ci adnotacji @RequestMapping
Adnotacje @RequestMapping mo�na r�wnie� stosowa� nad nazw� klasy. W�wczas odwo�anie do wszystkich metod w tej klasie b�dzie musia�o zosta� poprzedzone warto�ci� w niej podanej. Poni�szy przyk�ad prezentuje dodanie adnotacji do klasy.
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
Dla tego przypadku ka�de wywo�anie metody z tej klasy musi zosta� poprzedzone:
http://localhost:8080/api

Teraz, aby wywo�anie metody purchase() by�o mo�liwe nale�y odwo�a� si� do adresu:
http://localhost:8080/api/getExample

Metody GET, HEAD, POST, PUT, PATCH, DELETE, OPTIONS, TRACE
Domy�lnie adnotacja @RequestMapping wskazuje, �e jest to metoda GET. Jednak podajac dodatkowy paramter mo�na wskazac na dowoln� metod� HTTP.
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


