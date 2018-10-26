// component/modal/modal.js

const def = {
  setting: {
    show: false
  },
  show: false,
  fadeIn: false,
  title: '',
  content: '',
  align: 'left',
  cancel: false,
  confirmText: '确定',
  openType: ''
};

Component({
  
  properties: {
    setting: {
      type: Object,
      value: {},
      observer: '_onChange'
    }
  },

  data: {},

  methods: {
    _onChange: function (newVal, oldVal){

      let that = this;
      let data = newVal;

      if (!data){ return; }
      if (!data.show){ return; }

      that._showModal(data);
    },

    _showModal: function(data){
      
      let that = this;

      // console.log(that);

      // Set modal data
      that.setData({
        show: true,
        title: data.title ? data.title : def.title,
        content: data.content ? data.content : def.content,
        align: data.align ? data.align : 'left',
        cancel: data.cancel == undefined ? def.cancel : data.cancel,
        confirmText: data.confirmText ? data.confirmText : def.confirmText,
        openType: data.openType ? data.openType : ''
      });

      // Show modal
      setTimeout(function () {
        that.setData({
          fadeIn: true
        })
      }, 100);
    },

    // Hide modal
    _hideModal: function(){
      
      let that = this;

      // Hide Modal
      that.setData({
        fadeIn: false
      });

      // Reset modal data
      setTimeout(function(){
        that.setData(def);
      },300);
    },

    // onModalCancel
    onModalCancel: function(){

      let that = this;

      // Hide modal
      that._hideModal();

      // Trigger cancel
      this.triggerEvent('cancel');
    },

    // onModalConfirm
    onModalConfirm: function () {

      let that = this;

      // Hide modal
      that._hideModal();

      // Trigger cancel
      this.triggerEvent('confirm');
    },

    // onGotUserInfo
    onGotUserInfo: function(e){

      // Trigger getUserInfo
      this.triggerEvent('getUserInfo',e.detail);
    }

  },

  ready: function(){

  }

})
