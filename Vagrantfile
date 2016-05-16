# vi - set ft=ruby
Vagrant.require_version  ">= 1.8.1"
Vagrant.configure(2) do |config|

  config.vm.provision "Install PIP and Ansible", type: "shell", inline: <<-SHELL
    apt-get update -qq
    apt-get install -y -qq python-pip
    sudo pip install --quiet --log /tmp/pip.log --timeout 60 ansible==1.9.6 || echo 'DAMMIT!' 1>&2
  SHELL

  config.vm.provision "ansible_local" do |ansible|
    ansible.playbook       = "./provision.yml"
    ansible.verbose        = true
    ansible.install        = true
    ansible.version        = "1.9.6"
    ansible.limit          = "all"
    ansible.inventory_path = "./inventory"
  end

  config.vm.define "swinger", primary: true do |tester|
    tester.vm.box = "ubuntu/trusty64"
    tester.vm.hostname = "tester"

    tester.vm.provider "virtualbox" do |vbox|
      vbox.name = "progtech1 bead2 Swing tester"
      vbox.cpus = 2
      vbox.memory = 2048
      vbox.customize ["modifyvm", :id, "--vrde", "on", "--vrdeport", "3369"]
      #vbox.gui = true
    end
  end

  config.vm.provision "gradlew", type: "shell", inline: <<-SHELL
    cd /vagrant
    export DISPLAY=:0.0
    ./gradlew -q
  SHELL

end
