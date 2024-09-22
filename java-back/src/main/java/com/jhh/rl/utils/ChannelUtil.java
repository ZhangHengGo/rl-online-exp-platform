package com.jhh.rl.utils;

import com.jcraft.jsch.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Properties;

@Slf4j
@Component
public class ChannelUtil {

    @Value("${shell.host}")
    private String host;

    @Value("${shell.username}")
    private String username;

    @Value("${shell.port}")
    private Integer port;

    @Value("${shell.password}")
    private String password;

    @Value("${shell.file.path}")
    private String filePath;

    @Value("${shell.docker.file.path}")
    private String dockerPath;

    @Value("${shell.docker.dockerfilePath}")
    private String dockerfilePath;

    // 最关键的部分，能够将命令传输到主机
    // 输入：需要执行的命令
    // 输出：命令执行的结果
//    public String executeCommand(String command) {
//        JSch jsch = new JSch(); // 创建JSch对象
//        Session session = null; // 代表一个ssh会话，配置连接参数：用户名、密码、端口号等，并启动与远程服务器的连接
//        ChannelExec channelExec = null; // 用于执行远程命令
//        InputStream is = null; //用于接收远程命令返回的结果
//        BufferedReader reader = null; // 装饰了InputStream的读取器，用于提高读取效率
//        StringBuilder stringBuilder = new StringBuilder(); // 动态数组存储字符串，允许就地修改
//        try {
//            // 创建会话：用户名，主机地址，端口号
//            session = jsch.getSession(username, host, port);
//            // 设置密码
//            session.setPassword(password);
//            // 配置：禁用严格主机密钥检查，防止首次连接主机时，需要用户验证主机密钥的情况，使自动化脚本更加便捷
//            session.setConfig("StrictHostKeyChecking", "no");
//            // 设置超时时间：如果在50s内没有建立连接，则抛出异常
//            session.setTimeout(50000);
//            // 通过前面设置的参数尝试与远程服务器建立连接
//            session.connect();
//            // 用Session类的方法打开一个执行远程命令的通道
//            channelExec = (ChannelExec) session.openChannel("exec");
//            is = channelExec.getInputStream();
//            channelExec.setPty(true); // 提供一个伪终端
//            // 设置命令
//            channelExec.setCommand(command);
//            // 执行命令
//            channelExec.connect();
//            // 获取返回结果
//            // InputStream本身只能一次读取一个字节的数据，
//            // 而InputStreamReader将字节流转换成字符流，使得可以按字符而非字节来处理数据
//            // BufferedReader：对InputStreamReader的进一步封装，
//            // 提供了缓冲的读取，这意味着从输入流中读取数据时可以更高效
//            reader = new BufferedReader(new InputStreamReader(is));
//            String line;
//            while((line = reader.readLine()) != null){
//                stringBuilder.append((line));
//            }
//        }
//        // 这意味着无论try块中发生什么类型的异常（只要它们是Exception的子类），
//        // 都会被这个catch块处理
//        catch (Exception e) {
//            log.info("exec cmd error" + command);
//        }
//        // 不管是否发生异常，finally块中的代码总是会执行。
//        // 这用于清理在try块中打开的资源，确保它们即使在发生异常时也能被适当关闭
//        finally {
//            assert session != null;
//            session.disconnect();
//            assert channelExec != null;
//            channelExec.disconnect();
//            try {
//                assert is != null;
//                is.close();
//                assert reader != null;
//                reader.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        log.info(" result:" + stringBuilder.toString());
//        return stringBuilder.toString();
//    }
//    public String executeCommand(String command) {
//        JSch jsch = new JSch(); // 创建JSch对象
//        Session session = null; // 代表一个ssh会话，配置连接参数：用户名、密码、端口号等，并启动与远程服务器的连接
//        Channel channel = null; // 用于执行远程命令
//        ChannelShell shell = null; // 用于执行远程命令
//        StringBuilder output = new StringBuilder();
//        try {
//            // 创建会话：用户名，主机地址，端口号
//            session = jsch.getSession(username, host, port);
//            // 设置密码
//            session.setPassword(password);
//            Properties config = new Properties();
//            config.put("StrictHostKeyChecking", "no");
//            // 配置：禁用严格主机密钥检查，防止首次连接主机时，需要用户验证主机密钥的情况，使自动化脚本更加便捷
//            session.setConfig(config);
//            // 设置超时时间：如果在50s内没有建立连接，则抛出异常
//            session.setTimeout(50000);
//            // 通过前面设置的参数尝试与远程服务器建立连接
//            session.connect();
//            channel = session.openChannel("shell");
//            shell = (ChannelShell) channel;
//            channel.connect();
//
//            OutputStream out = shell.getOutputStream();
//            out.write(command.getBytes());
//            out.write("\n".getBytes());
//            out.flush();
//
//            InputStream in = channel.getInputStream();
//            byte[] tmp = new byte[1024];
//            while (true) {
//                while (in.available() > 0) {
//                    int i = in.read(tmp, 0, 1024);
//                    if (i < 0) break;
//                    String str = new String(tmp, 0, i);
//                    output.append(str);
//                }
//                if (in.available() == 0) {
//                    System.out.println("exit-status: " + channel.getExitStatus());
//                    break;
//                }
//                try {
//                    Thread.sleep(1000); // 等待1秒，以便命令执行完成
//                } catch (InterruptedException e) {
//                    Thread.currentThread().interrupt();
//                }
//            }
//            out.write("exit\n".getBytes());
//            out.flush();
//        }
//        // 这意味着无论try块中发生什么类型的异常（只要它们是Exception的子类），
//        // 都会被这个catch块处理
//        catch (Exception e) {
//            log.info("exec cmd error" + command);
//        }
//        // 不管是否发生异常，finally块中的代码总是会执行。
//        // 这用于清理在try块中打开的资源，确保它们即使在发生异常时也能被适当关闭
//        finally {
//            // 关闭会话
//            if (shell != null) {
//                shell.disconnect();
//            }
//            if (session != null) {
//                session.disconnect();
//            }
//        }
//        log.info(" result:" + output.toString());
//        return output.toString();
//    }
    public String executeCommand(String command) {
        JSch jsch = new JSch(); // 创建JSch对象
        Session session = null; // 代表一个ssh会话，配置连接参数：用户名、密码、端口号等，并启动与远程服务器的连接
        ChannelExec channelExec = null; // 用于执行远程命令
        InputStream is = null; //用于接收远程命令返回的结果
        BufferedReader reader = null; // 装饰了InputStream的读取器，用于提高读取效率
        StringBuilder stringBuilder = new StringBuilder(); // 动态数组存储字符串，允许就地修改
        try {
            // 创建会话：用户名，主机地址，端口号
            session = jsch.getSession(username, host, port);
            // 设置密码
            session.setPassword(password);
            // 配置：禁用严格主机密钥检查，防止首次连接主机时，需要用户验证主机密钥的情况，使自动化脚本更加便捷
            session.setConfig("StrictHostKeyChecking", "no");
            // 设置超时时间：如果在50s内没有建立连接，则抛出异常
            session.setTimeout(50000);
            // 通过前面设置的参数尝试与远程服务器建立连接
            session.connect();
            // 用Session类的方法打开一个执行远程命令的通道
            channelExec = (ChannelExec) session.openChannel("exec");
            is = channelExec.getInputStream();
            channelExec.setPty(true); // 提供一个伪终端
            // 设置命令
            channelExec.setCommand(command);
            // 执行命令
            channelExec.connect();
            // 获取返回结果
            // InputStream本身只能一次读取一个字节的数据，
            // 而InputStreamReader将字节流转换成字符流，使得可以按字符而非字节来处理数据
            // BufferedReader：对InputStreamReader的进一步封装，
            // 提供了缓冲的读取，这意味着从输入流中读取数据时可以更高效
            reader = new BufferedReader(new InputStreamReader(is));
            String line;
            // reader逐行读取数据，直到读不到数据为止
            // 用append不用+是为了避免频繁创建和销毁对象
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
        }
        // 这意味着无论try块中发生什么类型的异常（只要它们是Exception的子类），
        // 都会被这个catch块处理
        catch (Exception e) {
            log.info("exec cmd error" + command);
        }
        // 不管是否发生异常，finally块中的代码总是会执行。
        // 这用于清理在try块中打开的资源，确保它们即使在发生异常时也能被适当关闭
        finally {
            assert session != null;
            session.disconnect();
            assert channelExec != null;
            channelExec.disconnect();
            try {
                assert is != null;
                is.close();
                assert reader != null;
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        log.info(" result:" + stringBuilder.toString());
        return stringBuilder.toString();
    }
    // 通过SFTP（SSH文件传输协议）上传文件的Java方法
    public void uploadFile(MultipartFile file) {
        JSch jsch = new JSch();
        Session session = null;
        // ChannelSftp 是 JSch 库中用于 SFTP（SSH 文件传输协议）通信的类，
        // 负责在 SSH 会话中建立和管理 SFTP 通道
        ChannelSftp channelSftp = null;
        InputStream is = null;
        try {
            session = jsch.getSession(username, host, port);
            session.setPassword(password);
            session.setConfig("StrictHostKeyChecking", "no");
            session.setTimeout(50000);
            session.connect();
            // 创建Sftp客户端
            channelSftp = (ChannelSftp) session.openChannel("sftp");
            channelSftp.connect();
            is = file.getInputStream(); // 从文件中获取输入流
            String filename = file.getOriginalFilename(); // 获取原始文件名
            // 上传文件到指定的位置
            channelSftp.put(is, filePath + filename);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭会话
            assert session != null;
            session.disconnect();
            assert channelSftp != null;
            channelSftp.disconnect();
            try {
                assert is != null;
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // docker ps 列出容器
    // -a 列出所有，包括未运行的
    // -q 仅显示容器id
    // -f 过滤条件
    public String queryContainerId(String containerName) {
        String cmd = "docker ps -aqf \"name=" + containerName + "\"";
        return executeCommand(cmd);
    }

    // 用于在容器和本地文件系统之间复制文件
    // source 源文件
    // destination 目标文件
    public void dockerCp(String source, String destination) {
        String cmd = "docker cp " + source + " " + destination;
        executeCommand(cmd);
    }

    /**
     * 将指定文件上传到Docker容器中。
     * @param containerId Docker容器的ID。
     * @param filename 需要上传的文件名称。
     * 该方法不返回任何值。
     */
    public void dockerUpload(String containerId, String filename) {
        // 拼接文件的本地路径
        String source = filePath + filename;
        // 拼接容器内的目标路径
        String destination = containerId + ":" + dockerPath;
        // 执行Docker复制命令，将文件从本地复制到容器中
        dockerCp(source, destination);
    }

    /**
     * 从指定的Docker容器中下载文件。
     * @param containerId Docker容器的ID。
     * @param filename 需要下载的文件名称。
     * 此方法通过调用dockerCp函数，将指定容器内的文件复制到本地文件系统。
     */
    public void dockerDownload(String containerId, String filename) {
        // 构造容器内的文件路径
        String source = containerId + ":" + dockerPath + filename;
        // 指定本地保存文件的路径
        String destination = filePath;
        // 执行文件复制操作
        dockerCp(source, destination);
    }

    // 判断是否是一个文件路径
    public static boolean isPath(String input){
        //如果字符串以“~”开头或者以“/”开头，说明是路径
        return input.startsWith("~") || input.startsWith("/");
    }


    // 从Docker仓库拉取指定版本的镜像
    public Boolean pullImage(String version) {
        String cmd = "docker pull " + version;
        String result = executeCommand(cmd);
        if (result.contains("complete")) {
            return true;
        }
        return false;
    }

    public String buildDocker(String version, String path) {
        String cmd = "docker build -t " + version + " " + path;
        String imageID = executeCommand(cmd);
        return imageID;
    }
    public String rmiDocker(String version) {
        String cmd = "docker rmi " + version;
        String imageID = executeCommand(cmd);
        return imageID;
    }

    public String runDocker(Integer port, String dockerName, String version) {
        String cmd = "docker run -d -p " + port + ":80 --expose=5900 "  + " --name=\"" + dockerName + "\" " + version;
        String containerID = executeCommand(cmd);
        return containerID;
    }
    public String runDocker(String config, String dockerName, String version) {
        String cmd = "docker run -d " + config + " --name=\"" + dockerName + "\" " + version;
        String containerID = executeCommand(cmd);
        return containerID;
    }

    public void startDocker(String dockerName) {
        String cmd = "docker start " + dockerName;
        executeCommand(cmd);
    }

    public void restartDocker(String dockerID) {
        String cmd = "docker restart " + dockerID;
        executeCommand(cmd);
    }

    public void stopDocker(String dockerID) {
        String cmd = "docker stop " + dockerID;
        executeCommand(cmd);
    }

    public void rmDocker(String dockerID) {
        String cmd = "docker rm " + dockerID;
        executeCommand(cmd);
    }


    // 创建文件夹
    public String mkDir(String dir){
        String command = "mkdir -p " + filePath + dir;
        return executeCommand(command);
    }


}
