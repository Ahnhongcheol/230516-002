<template>
    <v-card outlined>
        <v-card-title>
            User # {{item._links.self.href.split("/")[item._links.self.href.split("/").length - 1]}}
        </v-card-title>

        <v-card-text>
            <div>
                <Number label="Uidx" v-model="item.uidx" :editMode="editMode" @change="change" />
            </div>
            <div>
                <String label="TenantId" v-model="item.tenantId" :editMode="editMode" @change="change" />
            </div>
            <div>
                <String label="UseStatus" v-model="item.useStatus" :editMode="editMode" @change="change" />
            </div>
            <div>
                <String label="Pass" v-model="item.pass" :editMode="editMode" @change="change" />
            </div>
            <div>
                <String label="UserName" v-model="item.userName" :editMode="editMode" @change="change" />
            </div>
            <div>
                <String label="CreateBy" v-model="item.createBy" :editMode="editMode" @change="change" />
            </div>
            <div>
                <String label="CreateTm" v-model="item.createTm" :editMode="editMode" @change="change" />
            </div>
            <div>
                <String label="LastModiBy" v-model="item.lastModiBy" :editMode="editMode" @change="change" />
            </div>
            <div>
                <String label="LastModiTm" v-model="item.lastModiTm" :editMode="editMode" @change="change" />
            </div>
            <DepartmentId offline label="DepartmentId" v-model="item.departmentId" :editMode="false" :key="false" @change="change" />
        </v-card-text>

        <v-card-actions>
            <v-btn text color="deep-purple lighten-2" large @click="goList">List</v-btn>
            <v-spacer></v-spacer>
            <v-btn
                    color="deep-purple lighten-2"
                    text
                    @click="edit"
                    v-if="!editMode"
            >
                Edit
            </v-btn>
            <v-btn
                    color="deep-purple lighten-2"
                    text
                    @click="save"
                    v-else
            >
                Save
            </v-btn>
            <v-btn
                    color="deep-purple lighten-2"
                    text
                    @click="remove"
                    v-if="!editMode"
            >
                Delete
            </v-btn>
            <v-btn
                    color="deep-purple lighten-2"
                    text
                    @click="editMode = false"
                    v-if="editMode"
            >
                Cancel
            </v-btn>
        </v-card-actions>
    </v-card>
</template>


<script>
    const axios = require('axios').default;

    export default {
        name: 'UserDetail',
        components:{},
        props: {
        },
        data: () => ({
            item: null,
            editMode: false,
        }),
        async created() {
            var me = this;
            var params = this.$route.params;
            var temp = await axios.get(axios.fixUrl('/users/' + params.id))
            if(temp.data) {
                me.item = temp.data
            }
        },
        methods: {
            goList() {
                var path = window.location.href.slice(window.location.href.indexOf("/#/"), window.location.href.lastIndexOf("/#"));
                path = path.replace("/#/", "/");
                this.$router.push(path);
            },
            edit() {
                this.editMode = true;
            },
            async remove(){
                try {
                    if (!this.offline) {
                        await axios.delete(axios.fixUrl(this.item._links.self.href))
                    }

                    this.editMode = false;

                    this.$emit('input', this.item);
                    this.$emit('delete', this.item);

                } catch(e) {
                    console.log(e)
                }
            },
        },
    };
</script>
