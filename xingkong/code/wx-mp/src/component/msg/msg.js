// component/msg/msg.js

Component({

  properties: {
    msgShow: {
      type: Boolean,
      value: false,
      observer: '_changeShown'
    },
    msgMask: {
      type: Boolean,
      value: false
    },
    msgContent: String
  },

  data: {
    showMsgWrap: false,
    showMsg: false,
    showMask: false
  },

  methods: {

    hideMsg: function(){

      // Trigger close
      this.triggerEvent('hideMsg');
    },

    _changeShown: function (newVal, oldVal){

      // console.log('newVal', newVal);
      // console.log('oldVal', oldVal);
      // console.log(this.data);

      let that = this;

      // Show msg
      if (newVal){

        // Show wrap
        that.setData({ showMsgWrap: true});

        // Show content
        setTimeout(function () {
          that.setData({
            showMsg: true
          })

        }, 10);
      }

      // Hide msg
      else {

        // Hide content
        that.setData({
          // showMsgWrap: false,
          showMsg: false,
          showMask: false
        });

        setTimeout(function () {
          
          // Hide wrap
          that.setData({ showMsgWrap: false });

        }, 300);
      }
      
    }
  }
})
