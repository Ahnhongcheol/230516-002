<template>

    <v-data-table
        :headers="headers"
        :items="userView"
        :items-per-page="5"
        class="elevation-1"
    ></v-data-table>

</template>

<script>
    const axios = require('axios').default;

    export default {
        name: 'UserViewView',
        props: {
            value: Object,
            editMode: Boolean,
            isNew: Boolean
        },
        data: () => ({
            headers: [
                { text: "id", value: "id" },
            ],
            userView : [],
        }),
          async created() {
            var temp = await axios.get(axios.fixUrl('/userViews'))

            temp.data._embedded.userViews.map(obj => obj.id=obj._links.self.href.split("/")[obj._links.self.href.split("/").length - 1])

            this.userView = temp.data._embedded.userViews;
        },
        methods: {
        }
    }
</script>

