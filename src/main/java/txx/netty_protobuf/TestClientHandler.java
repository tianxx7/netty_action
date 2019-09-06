package txx.netty_protobuf;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/* *
 * 描述:
 * @user tianxinxing
 * @date 2019/9/6
 */
public class TestClientHandler extends SimpleChannelInboundHandler<MyDataInfo.Person> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MyDataInfo.Person msg) throws Exception {

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        MyDataInfo.Person person = MyDataInfo.Person.newBuilder().setName("田新兴")
                .setAge(17).setAddress("南京").build();
        channel.writeAndFlush(person);
    }
}
