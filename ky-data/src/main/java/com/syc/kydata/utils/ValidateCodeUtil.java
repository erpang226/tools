package com.syc.kydata.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Description:
 *
 * @version V1.0
 * date: 2019/9/3
 * author: song yong chang
 */
@Component
public class ValidateCodeUtil {
    private static final Logger logger = LoggerFactory.getLogger(ValidateCodeUtil.class);

    @Autowired
    private StringRedisTemplate redisTemplate;

    private static final String RANDOM_CODEKEY = "RANDOMVALIDATECODEKEY";//放到session中的key
    private static final String RAND_STRING = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";//随机产生数字与字母组合的字符串
    private static final int WIDTH = 95;// 图片宽
    private static final int HEIGHT = 25;// 图片高
    private static final int LINE_SIZE = 40;// 干扰线数量
    private static final int STRING_NUM = 4;// 随机产生字符数量

    private Random random = new Random();

    /**
     * 获得颜色
     */
    private Color getRandColor(int fc, int bc) {

        if (fc > 255)
            fc = 255;
        if (bc > 255)
            bc = 255;
        int r = fc + random.nextInt(bc - fc - 16);
        int g = fc + random.nextInt(bc - fc - 14);
        int b = fc + random.nextInt(bc - fc - 18);
        return new Color(r, g, b);
    }

    /**
     * 生成随机图片,将随机的字符保存在session中
     */
    public void getRandomCode(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        // BufferedImage类是具有缓冲区的Image类,Image类是用于描述图像信息的类
        BufferedImage image = getBufferedImageSaveIntoSession(session);
        try {
            // 将内存中的图片通过流动形式输出到客户端
            ImageIO.write(image, "JPEG", response.getOutputStream());
        } catch (Exception e) {
            logger.error("将图片流动输出到客户端失败", e);
        }

    }

    private BufferedImage getBufferedImageSaveIntoSession(HttpSession session) {
        BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_BGR);
        Graphics g = getGraphics(image);
        String randomString = createPicture(g);
        g.dispose();
        logger.info("validate code is {}, session key is {}", randomString, RANDOM_CODEKEY);
        //将生成的随机字符串保存到session中
        session.removeAttribute(RANDOM_CODEKEY);
        session.setAttribute(RANDOM_CODEKEY, randomString);
        return image;
    }

    private String createPicture(Graphics g) {
        // 绘制干扰线
        for (int i = 0; i <= LINE_SIZE; i++) {
            drawLine(g);
        }
        // 绘制随机字符
        return getString(g);
    }

    private String getString(Graphics g) {
        String randomString = "";
        for (int i = 1; i <= STRING_NUM; i++) {
            randomString = drawString(g, randomString, i);
        }
        return randomString;
    }


    /**
     * 生成随机图片,将随机的字符保存在redis中
     */
    public void getRandomCode(String key, HttpServletResponse response) {
        // BufferedImage类是具有缓冲区的Image类,Image类是用于描述图像信息的类
        BufferedImage image = getBufferedImageSaveIntoRedis(key);
        try {
            // 将内存中的图片通过流动形式输出到客户端
            ImageIO.write(image, "JPEG", response.getOutputStream());
        } catch (Exception e) {
            logger.error("将图片流输出到客户端失败", e);
        }

    }

    private BufferedImage getBufferedImageSaveIntoRedis(String key) {
        BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_BGR);
        Graphics g = getGraphics(image);
        // 绘制干扰线
        String randomString = createPicture(g);
        g.dispose();
        logger.info("validate code is {}, redis key is {}", randomString, key);
        redisTemplate.opsForValue().set(key, randomString, 60, TimeUnit.SECONDS);
        return image;
    }

    private Graphics getGraphics(BufferedImage image) {
        Graphics g = image.getGraphics();// 产生Image对象的Graphics对象,改对象可以在图像上进行各种绘制操作
        g.fillRect(0, 0, WIDTH, HEIGHT);//图片大小
        g.setFont(new Font("Times New Roman", Font.ITALIC, 18));//字体大小
        g.setColor(getRandColor(110, 133));//字体颜色
        return g;
    }


    /**
     * 绘制字符串
     */
    private String drawString(Graphics g, String randomString, int i) {
        Font font = new Font("Fixedsys", Font.ITALIC, 18);
        g.setFont(font);
        g.setColor(new Color(random.nextInt(101), random.nextInt(111), random
                .nextInt(121)));

        String rand = String.valueOf(RAND_STRING.charAt(random.nextInt(RAND_STRING.length())));
        randomString += rand;
        g.translate(random.nextInt(3), random.nextInt(3));
        g.drawString(rand, 13 * i, 16);
        return randomString;
    }

    /**
     * 绘制干扰线
     */
    private void drawLine(Graphics g) {
        int x = random.nextInt(WIDTH);
        int y = random.nextInt(HEIGHT);
        int xl = random.nextInt(13);
        int yl = random.nextInt(15);
        g.drawLine(x, y, x + xl, y + yl);
    }

    public String getValidateCodeFromRedis(String key){
        String code = redisTemplate.opsForValue().get(key);
        return code;
    }

}

