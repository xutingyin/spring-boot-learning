package cn.xutingyin.springbootnetty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;
/**
 * 自定义的报文解析器 
 * 	解析 8位报文长度头+报文体
 */
public class SelfDefineDecoder extends ByteToMessageDecoder {

	//定义的报文长度头
	public static final  int HEAD_LEN = 8;
	
	public static int count = 0;
	
	@Override
	protected void decode(ChannelHandlerContext cxt, ByteBuf bufIn,
			List<Object> out) throws Exception {
		
//System.out.println("第"+(++count)+"次调用decode");
		//如果可读字节小于八位长度头，直接返回
		if(bufIn.readableBytes() < HEAD_LEN ){
			return;
		}
		//标记读取开始位置
		int beginIndex = bufIn.readerIndex();
		//读取八位报文长度头
		byte[] bytes = new byte[8];
		bufIn.readBytes(bytes);
		String s = new String(bytes);
//System.out.println(s);
		//得到报文长度
		int length = Integer.parseInt(s);
		//计算：如果可读取长度小于获取的报文长度，则直接返回，等待包来齐
		if(bufIn.readableBytes() + 1 < length){
			//恢复到读取初始位置
			bufIn.readerIndex(beginIndex);
			return;
		}
		//读取一个完整的报文
		bufIn.readerIndex(beginIndex + HEAD_LEN + length);
		ByteBuf bufRef = bufIn.slice(beginIndex, HEAD_LEN + length);
		bufRef.retain();
		out.add(bufRef);
	}

}
