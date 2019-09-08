package txx.netty_thrift;

import org.apache.thrift.TException;
import txx.thrift.generated.DataException;
import txx.thrift.generated.Person;
import txx.thrift.generated.PersonService;

/* *
 * 描述:
 * @user tianxinxing
 * @date 2019/9/8
 */
public class PersonServiceImpl implements PersonService.Iface {
    @Override
    public Person getPersonByUsername(String username) throws DataException, TException {
        System.out.println("out client Param:" + username);
        Person person = new Person();
        person.setUsername(username);
        person.setAge(12);
        person.setMarried(false);
        return person;
    }

    @Override
    public void savePerson(Person person) throws DataException, TException {
        System.out.println(person.getAge());
        System.out.println(person.getUsername());
        System.out.println(person.isMarried());
    }
}
