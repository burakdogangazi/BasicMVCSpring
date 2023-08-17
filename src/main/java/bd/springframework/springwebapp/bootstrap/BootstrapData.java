package bd.springframework.springwebapp.bootstrap;

import bd.springframework.springwebapp.domain.Author;
import bd.springframework.springwebapp.domain.Book;
import bd.springframework.springwebapp.domain.Publisher;
import bd.springframework.springwebapp.repositories.AuthorRepository;
import bd.springframework.springwebapp.repositories.BookRepository;
import bd.springframework.springwebapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private  final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Author burak = new Author();
        burak.setFirstName("Burak");
        burak.setLastName("Doğan");

        Book burakExBook = new Book();
        burakExBook.setIsbn("123123");
        burakExBook.setTitle("Domain Drive Desing");

        Author burakSaved = authorRepository.save(burak);
        Book burakBookSaved = bookRepository.save(burakExBook);


        Author metin = new Author();
        burak.setFirstName("Metin");
        burak.setLastName("Doğan");

        Book metinExBook = new Book();
        metinExBook.setIsbn("123125");
        metinExBook.setTitle("Constructors");

        Author metinSaved = authorRepository.save(metin);
        Book metinBookSaved = bookRepository.save(metinExBook);

        burakSaved.getBooks().add(burakBookSaved);
        metinSaved.getBooks().add(metinBookSaved);

        Publisher examplePublisher = new Publisher();
        examplePublisher.setPublisherName("Ex publisher");
        examplePublisher.setState("Tr");
        examplePublisher.setAddress("Ankara Dk");
        Publisher savedPublisher = publisherRepository.save(examplePublisher);

        burakBookSaved.setPublisher(savedPublisher);
        metinBookSaved.setPublisher(savedPublisher);


        bookRepository.save(burakBookSaved);
        bookRepository.save(metinBookSaved);
        authorRepository.save(burakSaved);
        authorRepository.save(metinSaved);


        System.out.println("In Bootstrap");
        System.out.println("Author Count : " + authorRepository.count());
        System.out.println("Book count : "+ bookRepository.count());
        System.out.println("Publisher count : "+ publisherRepository.count());


    }

    /*
     BootstrapData class. This class undertakes the implementation of the CommandLineRunner interface.

      To seamlessly integrate it into the Spring framework, we apply the @Component annotation. This decoration serves as a flag, notifying Spring that this class should be treated as a component. Consequently, when Spring conducts its package scanning, it identifies the BootstrapData class and subsequently generates a dedicated Spring bean instance.

      During the wiring process, an essential aspect comes into play: the class's constructor. Since there's only one constructor available, Spring Data JPA's pre-implemented implementations for both AuthorRepository and BookRepository are employed. Notably, we authored the interfaces, leaving Spring Data to provide the concrete implementation details.

      These implementations are seamlessly integrated into the Spring context, functioning as beans accessible throughout the application. The mechanism works as follows: when Spring detects the BootstrapData component, it discerns that the constructor necessitates the automatic injection of these components.

     Finally, aligning with the requirements of the CommandLineRunner interface, we are obliged to implement the run method within the BootstrapData class. Within this method, additional custom logic can be inserted to cater to specific application needs.
     */
}
