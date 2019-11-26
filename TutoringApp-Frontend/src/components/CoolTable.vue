<template>
  <div class="cool-table" v-bind:style="'width:' + width">
    <div class="container">
        <div
            tabindex="0"
            ref="scroll"
            class="scroll"
            v-on:keydown.up='$event.preventDefault(); select(selected-1)'
            v-on:keydown.down='$event.preventDefault(); select(selected+1)'
            v-on:keypress='search()'>
            <table style="width: 100%" ref="table">
                <tr v-if="title != undefined">
                    <th class="title" style="text-align: center" v-bind:colspan="columns.length">
                        {{ title }}
                    </th>
                </tr>
                <tr>
                    <th
                        v-for="header in headers"
                        v-bind:key="header.name"
                        v-bind:class="title == undefined ? 'title' : ''"
                        v-bind:style="'width: ' + header.width">
                        {{ header.name }}
                    </th>
                </tr>
                <tr
                    v-for="index in list.length"
                    v-bind:key="index"
                    v-bind:tabindex="index"
                    v-on:mousedown="select(index)"
                    v-bind:class="[selected == index ? 'highlight' : '']">
                    <td v-for="column in columns" v-bind:key="column.hash">
                        <template v-if="typeof column == 'string'">
                            {{ list[index-1][column] }}
                        </template>
                        <template v-else-if="typeof column == 'function'">
                            {{ column.call(list[index-1]) }}
                        </template>
                        <template v-else>
                            <select
                                v-model='list[index-1][column.property]'
                                v-on:change="column.onChange(index-1)">
                                    <option
                                        v-for="opt in column.options"
                                        v-bind:key="opt.value"
                                        v-bind:value="opt.value">
                                            {{ opt.display }}
                                    </option>
                            </select>
                        </template>
                    </td>
                </tr>
            </table>
        </div>
        <input
        ref="searchbox"
        style="display: none"
        v-model="query"
        v-on:keydown.esc="$refs.scroll.focus()"
        v-on:focusout="unsearch()" />
    </div>
  </div>
</template>


<script>
export default {
    name: "cool-table",
    props:
    {
        title: { type: String, required: false },
        headers: { type: Array, required: true },
        columns: { type: Array, required: true },
        list: { type: Array, required: true },
        searchid: { type: String, required: true },
        width: { type: String, required: false, default: '100%' }
    },
    data: function() {
        return { selected: undefined, query: undefined };
    },
    methods:
    {
        select: function(index)
        {
            if (index < 1 || index > this.list.length)
                return;
            this.selected = index;
            this.$refs.table.rows[index].focus();
        },
        search: function (query)
        {
            this.$refs.searchbox.style.display = "initial";
            this.query = query;
            this.$refs.searchbox.focus();
        },
        unsearch: function()
        {
            this.$refs.searchbox.style.display = "none";
        }
    },
    watch:
    {
        query: function (val)
        {
            this.selected = 0;
            if (this.query == "")
                this.query = undefined;
            for (var i = 0; i < this.list.length; i++)
            {
                if (this.list[i][this.searchid].startsWith(this.query))
                {
                    this.select(i+1);
                    this.search(this.query);
                    break;
                }
            }
        }
    }
}
</script>

<style src="./Style.css" />
<style scoped>

div.container {
    text-align: right;
}

div.scroll {
  margin-top: 20px;
  margin: auto;
  height: 500px;
  width: 100%;
  padding: 5px;
  text-align: left;
  overflow-y: auto;
  border: 3px solid #5c9bb7;
}

div.scroll:focus, tr:focus { outline: none; }

table .title { background-color: #5c9bb7; font-size: 14pt; padding: 2px; }
td { font-size: 12pt; }

.highlight { background-color: rgb(0, 255, 255); }
</style>
