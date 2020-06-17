package dawidkruczek.projectII.librarysystem.support.publisher;

import dawidkruczek.projectII.librarysystem.exception.EntityNotFoundException;
import dawidkruczek.projectII.librarysystem.model.Publisher;
import dawidkruczek.projectII.librarysystem.repository.PublisherRepository;
import dawidkruczek.projectII.librarysystem.support.AnswerType;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.validation.Validation;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PublisherService {
    private PublisherRepository repository;

    public PublisherService(PublisherRepository repository) {
        this.repository = repository;
    }

    public List<Publisher> getAllPublishers() {
        List<Publisher> publishers = repository.findAll();

        if(publishers.isEmpty()) {
            throw new EntityNotFoundException();
        }
        else {
            return publishers;
        }
    }

    public Optional<Publisher> getPublisher(String id) {
        Optional<Publisher> publisher = repository.findById(id);

        if(publisher.isPresent()) {
            return publisher;
        } else {
            throw new EntityNotFoundException(id);
        }
    }

    public List<String> addPublisher(Publisher publisher) {
        return prepareAnswers(AnswerType.ADDED,publisher);
    }

    public List<String>  updatePublisher(String id, Publisher newPublisher) {
        List<String> publishers;
        Optional<Publisher> oldPublisher = repository.findById(id);

        if(oldPublisher.isPresent()) {
            publishers = prepareAnswers(AnswerType.UPDATED,newPublisher);
            newPublisher.setId(id);
        }
        else {
            throw new EntityNotFoundException(id);
        }
        return publishers;
    }

    public HttpStatus deletePublisher(String id) {
        Optional<Publisher> publisher = repository.findById(id);
        if(publisher.isPresent()) {
            repository.delete(publisher.get());
            return HttpStatus.OK;
        }
        else {
            throw new EntityNotFoundException(id);
        }
    }

    public List<String> prepareAnswers(AnswerType type, Publisher publisher) {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        List<String > answers = new ArrayList<>();
        if(validator.validate(publisher).size() == 0) {
            repository.save(publisher);
            answers.add(publisher.getName());
            answers.add(type.toString());
        }
        else {
            answers.add("Wrong data: ");
            validator.validate(publisher).forEach(v->answers.add(v.getMessage()));
        }

        return answers;
    }
}
