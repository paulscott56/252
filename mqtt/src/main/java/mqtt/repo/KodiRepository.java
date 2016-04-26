package mqtt.repo;

import mqtt.model.KodiTitle;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by paul on 2016/04/26.
 */
public interface KodiRepository extends MongoRepository<KodiTitle, String> {

}
