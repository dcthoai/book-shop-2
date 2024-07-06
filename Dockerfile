FROM tomcat:9.0

WORKDIR /app

# Xóa ứng dụng web mặc định
RUN rm -rf /usr/local/tomcat/webapps/*

# Tạo thư mục uploads
RUN mkdir -p /usr/local/tomcat/uploads

# Sao chép ứng dụng WAR
COPY *.war /usr/local/tomcat/webapps/

# Sao chép thư mục uploads từ máy chủ vào image
COPY uploads/ /usr/local/tomcat/uploads/

EXPOSE 8080

CMD ["catalina.sh", "run"]
