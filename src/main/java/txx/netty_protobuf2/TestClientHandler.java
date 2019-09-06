package txx.netty_protobuf2;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Random;

/* *
 * 描述:
 * @user tianxinxing
 * @date 2019/9/6
 */
public class TestClientHandler extends SimpleChannelInboundHandler<MyDataInfo.MyMessage> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MyDataInfo.MyMessage msg) throws Exception {

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        int random  = new Random().nextInt(3);
        MyDataInfo.MyMessage message = null;
        if (0 == random) {
            MyDataInfo.Person person = MyDataInfo.Person.newBuilder().setAddress("南京").setAge(16).setName("人").build();
            message = MyDataInfo.MyMessage.newBuilder()
                    .setDataType(MyDataInfo.MyMessage.DataType.PersonType)
                    .setPerson(person).build();
        } else if (1 == random){
            MyDataInfo.Dog dog = MyDataInfo.Dog.newBuilder().setAge(16).setName("狗").build();
            message = MyDataInfo.MyMessage.newBuilder()
                    .setDataType(MyDataInfo.MyMessage.DataType.DogType)
                    .setDog(dog).build();
        } else {
            MyDataInfo.Cat cat = MyDataInfo.Cat.newBuilder().setCity("nanjing").setName("猫").build();
            message = MyDataInfo.MyMessage.newBuilder()
                    .setDataType(MyDataInfo.MyMessage.DataType.CatType)
                    .setCat(cat).build();
        }
        Channel channel = ctx.channel();
        channel.writeAndFlush(message);
    }
}
