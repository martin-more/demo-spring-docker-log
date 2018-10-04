### Running Docker

```sh
$ docker build -t demo-spring-docker-log -f src/main/docker/Dockerfile .
```

### Driver Log
[Docker Driver](https://docs.docker.com/config/containers/logging/configure/#configure-the-default-logging-driver)

```sh
$ docker run -p 8080:8080 --log-driver=syslog --log-opt syslog-address=udp://192.168.0.32:514 --log-opt syslog-facility=daemon --log-opt syslog-format=rfc5424 --log-opt tag="{{.ImageName}}/{{.ID}}" demo-spring-docker-log
```

### Syslog

- provides UDP syslog reception

module(load="imudp")
input(type="imudp" port="514")

- provides TCP syslog reception

module(load="imtcp")
input(type="imtcp" port="514")

- syntax for forcing listener address

input(type="imtcp" port="514" address="127.0.0.1")


### Restart service

# restart using systemd
```sh
$ sudo systemctl restart rsyslog

# show syslog logs using systemd journal (opt)
$ sudo journalctl -u rsyslog
```

### Application Logs

Create '50-demo-spring-docker-log.conf'

if $programname == 'demo-spring-docker-log' or $syslogtag == 'demo-spring-docker-log' then
  /var/log/demo-spring-docker-log/demo-spring-docker-log.log
& stop

### Create the log directory and restart the rsyslog service:

```sh
$ sudo mkdir -p /var/log/demo-spring-docker-log
$ sudo chown syslog:adm /var/log/demo-spring-docker-log
$ sudo chmod 755 /var/log/demo-spring-docker-log

# restart service on Fedora
$ sudo systemctl restart rsyslog
```
Source: [Ubuntu Syslog](https://fabianlee.org/2017/05/24/ubuntu-enabling-syslog-on-ubuntu-and-custom-templates/)
