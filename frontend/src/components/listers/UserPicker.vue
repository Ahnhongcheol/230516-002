<template>
    <div>
        <v-card-title @click="openDialog" style="cursor: pointer;">
            User : 
        </v-card-title>

        <v-dialog v-model="pickerDialog">
            <v-card>
                <v-card-title>User</v-card-title>
                <v-card-text>
                    <v-list two-line v-if="list.length > 0">
                        <v-list-item-group 
                                v-model="selected" 
                                color="indigo"
                                @change="select"
                        >
                            <v-list-item v-for="(item, idx) in list" :key="idx">
                                <template v-slot:default="{ active }">
                                    <v-list-item-avatar color="grey darken-1">
                                    </v-list-item-avatar>
                                    
                                    <v-list-item-content>
                                        <v-list-item-title>
                                        </v-list-item-title>
                                        <v-list-item-subtitle>
                                            Uidx :  {{item.uidx }}
                                        </v-list-item-subtitle>
                                        <v-list-item-subtitle>
                                            TenantId :  {{item.tenantId }}
                                        </v-list-item-subtitle>
                                        <v-list-item-subtitle>
                                            UseStatus :  {{item.useStatus }}
                                        </v-list-item-subtitle>
                                        <v-list-item-subtitle>
                                            Pass :  {{item.pass }}
                                        </v-list-item-subtitle>
                                        <v-list-item-subtitle>
                                            UserName :  {{item.userName }}
                                        </v-list-item-subtitle>
                                        <v-list-item-subtitle>
                                            CreateBy :  {{item.createBy }}
                                        </v-list-item-subtitle>
                                        <v-list-item-subtitle>
                                            CreateTm :  {{item.createTm }}
                                        </v-list-item-subtitle>
                                        <v-list-item-subtitle>
                                            LastModiBy :  {{item.lastModiBy }}
                                        </v-list-item-subtitle>
                                        <v-list-item-subtitle>
                                            LastModiTm :  {{item.lastModiTm }}
                                        </v-list-item-subtitle>
                                        <v-list-item-subtitle>
                                            DepartmentId :  {{item.departmentId }}
                                        </v-list-item-subtitle>
                                    </v-list-item-content>

                                    <v-list-item-action>
                                        <v-checkbox :input-value="active" color="indigo"></v-checkbox>
                                    </v-list-item-action>
                                </template>
                            </v-list-item>
                        </v-list-item-group>
                    </v-list>
                </v-card-text>
                <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn color="deep-purple lighten-2" text @click="pickerDialog = false">Close</v-btn>
                </v-card-actions>
            </v-card>
        </v-dialog>
    </div>
</template>


<script>
    const axios = require('axios').default;

    export default {
        name: 'UserPicker',
        props: {
            value: [String, Object, Array, Number, Boolean],
            editMode: Boolean,
        },
        data: () => ({
            list: [],
            selected: null,
            referenceValue: null,
            pickerDialog: false,
        }),
        async created() {
            var me = this;
            var temp = await axios.get(axios.fixUrl('/users'))
            if(temp.data) {
                me.list = temp.data._embedded.users;
            }

            if(me.value && typeof me.value == "object" && Object.values(me.value)[0]) {
                var idKey = 'id'
                
                
                
                
                
                
                
                
                
                
                var id = me.value[idKey];
                var tmpValue = await axios.get(axios.fixUrl('/users/' + id))
                if(tmpValue.data) {
                    var val = tmpValue.data
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    me.referenceValue = val
                }
            }
        },
        methods: {
            openDialog() {
                if (this.editMode) {
                    this.pickerDialog = true
                } else {
                    var idKey = 'id'
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    var id = this.value[idKey];
                    var path = '/users/';
                    this.$router.push(path + id);
                }
            },
            select(val) {
                var obj = {}
                if(val != undefined) {
                    var arr = this.list[val]._links.self.href.split('/');
                    obj['id'] = arr[4]; 
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    this.$emit('selected', obj);
                    this.referenceValue = this.list[val];
                }
            },
        },
    };
</script>

