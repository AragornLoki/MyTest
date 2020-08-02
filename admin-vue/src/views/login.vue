<template>
  <div class="login-container">
    <div class="login-wrap">
      <ul class="menu-tab">
        <li class= "current" >登录1</li>
      </ul>
    <el-form ref="loginForm" :model="loginForm" :rules="loginRules"  class="login-form" auto-complete="on" label-position="left">
      <el-form-item prop="username" class="item-form">
        <label>用户名</label>
        <el-input v-model="loginForm.username" name="username" type="text"  autocomplete="on" maxlength="20" placeholder="name"/>
      </el-form-item>
      <el-form-item prop="password" class="item-form">
        <label>密码</label>
        <el-input v-model="loginForm.password" name="password" :type="passwordType"  autocomplete="off" maxlength="20" placeholder="password"/>
        <span class="show-pwd" @click="showPwd">
          <svg-icon :icon-class="openeye" />
        </span>
      </el-form-item>

      <el-button
        type="primary"
        size="medium"
        class="login-box block"
        @click.native.prevent="handleLogin">
        登陆
      </el-button>
    </el-form>
    </div>
    <a href="http://localhost:80/download?name=骆.txt">下载</a>
  </div>

</template>

<script>
    export default {
        name: "login",
      data(){
        const validatePassword = (rule, value, callback) => {
          if (value.length < 6) {
            callback(new Error('管理员密码长度应大于6'))
          } else {
            callback()
          }
        }
        return {
          loginForm: {
            username: '',
            password: ''
          },
          loginRules: {
            username: [{ required: true, message: '管理员账户不允许为空', trigger: 'blur' }],
            password: [
              { required: true, message: '管理员密码不允许为空', trigger: 'blur' },
              { validator: validatePassword, trigger: 'blur' }
            ]
          },
          passwordType: 'password',
          openeye:'eye',
          loading: false
        }
      },
      methods: {
        showPwd() {
          if (this.passwordType === 'password') {
            this.passwordType = ''
            this.openeye='eye-open'
          } else {
            this.passwordType = 'password'
            this.openeye='eye'
          }
        },
        handleLogin() {
          this.$refs.loginForm.validate(valid => {
            if (valid && !this.loading) {
              this.loading = true
              this.$store.dispatch('LoginByUsername', this.loginForm)
                .then(() => {
                this.loading = false
                this.$router.push({ path: this.redirect || '/home' })
              }).catch(response => {
                console.log(response)
                this.$notify.error({
                  title: '失败',
                  message:'11' //response.data.errmsg
                })
                this.loading = false
              })
            } else {
              return false
            }
          })
        }
      }
    }
</script>

<style lang="scss">
  $bg: #ffffff;
  $light_gray:#fff;
  $cursor: #ffffff;


  /* reset element-ui css */
  .login-container {

    .el-input {
      display: inline-block;
      height: 47px;
      width: 100%;
      margin: 10px 0 0 0 ;
      input {

        -webkit-appearance: none;
        border-radius: 5px;

        margin:0 100px 0 0;

        height: 40px;


        &:-webkit-autofill {
          box-shadow: 0 0 0 1000px $bg inset !important;
          -webkit-text-fill-color: black !important;
        }
      }
    }

  }
</style>

<style lang="scss" scoped>
  $bg: #f6f6f6;
  $dark_gray:#889aa4;
  $light_gray: #000000;

  .login-container {
    min-height: 100%;
    width: 100%;
    background-color: $bg;
    overflow: hidden;
    .login-wrap {
      position: absolute;
      left: 50%;
      top: 50%;
      transform:translate(-50%,-65%);
      width: 330px;
      margin: auto;
      padding: 20px;
      border-radius: 2px;
      box-shadow: 10px 0 16px rgba(26,26,26,.1),
      0 10px 16px rgba(26,26,26,.1);

    }
    .menu-tab {
      text-align: center;
      li {
        display: inline-block;
        width: 88px;
        line-height: 36px;
        font-size: 18px;
        color: black;
        border-radius: 2px;
        cursor: pointer;
      }
      .current {
        background-color: rgba(32, 69, 233, 0.1);
      }
    }

    .login-form {
      position: relative;
      width: 520px;
      max-width: 100%;
      margin: 0 auto;
      overflow: hidden;
      .item-form{margin-bottom: 0px;}
      .block{
        display: block;
        width: 100%;
      }
      label{
        padding: 0;
        height: 28px;
        display: block;
        text-align: left;
        color: black;
        margin-top: 10px;
        //margin-bottom: 3px;
        font-size: 14px;
      }
      .login-box{
        margin-top: 20px;
      }
    }

    .svg-container {
      padding: 6px 5px 6px 15px;
      color: $dark_gray;
      vertical-align: middle;
      width: 30px;
      display: inline-block;
    }

    .title-container {
      position: relative;

      .title {
        font-size: 26px;
        color: black;
        margin: 0 auto 40px auto;
        text-align: center;
        font-weight: bold;
      }
    }

    .show-pwd {
      position: absolute;
      right: 10px;
      top: 7px;
      font-size: 16px;
      color: $dark_gray;
      cursor: pointer;
      user-select: none;
    }
  }
</style>
