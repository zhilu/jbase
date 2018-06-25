import React, {Component} from 'react'
import {Layout} from 'antd';

const {Header, Footer, Sider, Content} = Layout;
import {Menu, Icon} from 'antd';

const SubMenu = Menu.SubMenu;
const MenuItemGroup = Menu.ItemGroup;

class App extends Component {

    render() {
        return (
            <Layout>
                <Sider theme="dark">
                    <Menu
                        onClick={this.handleClick}
                        style={{width: 240}}
                        defaultSelectedKeys={['1']}
                        defaultOpenKeys={['sub1']}
                        mode="inline"
                    >
                        <SubMenu key="sub1" title={<span><Icon type="mail"/><span>客户管理</span></span>}>
                            <Menu.Item key="1">客户信息</Menu.Item>
                            <Menu.Item key="2">黑名单</Menu.Item>
                            <Menu.Item key="3">额度管理</Menu.Item>
                            <Menu.Item key="4">客户状态</Menu.Item>
                        </SubMenu>
                        <SubMenu key="sub2" title={<span><Icon type="appstore"/><span>规则管理</span></span>}>
                            <Menu.Item key="5">Option 5</Menu.Item>
                            <Menu.Item key="6">Option 6</Menu.Item>
                            <SubMenu key="sub3" title="Submenu">
                                <Menu.Item key="7">Option 7</Menu.Item>
                                <Menu.Item key="8">Option 8</Menu.Item>
                            </SubMenu>
                        </SubMenu>
                        <SubMenu key="sub4" title={<span><Icon type="setting"/><span>Navigation Three</span></span>}>
                            <Menu.Item key="9">Option 9</Menu.Item>
                            <Menu.Item key="10">Option 10</Menu.Item>
                            <Menu.Item key="11">Option 11</Menu.Item>
                            <Menu.Item key="12">Option 12</Menu.Item>
                        </SubMenu>
                    </Menu>
                </Sider>

                <Layout>
                    <Header>header</Header>
                    <Content>
                        ContentContentContentContentContent
                        ContentContentContentContentContent
                        ContentContentContentContentContentContentContentContentContentContent
                        ContentContentContentContentContent
                        ContentContentContentContentContent
                        ContentContentContentContentContent
                        ContentContentContentContentContentContentContentContentContentContent
                        ContentContentContentContentContent
                        ContentContentContentContentContent
                        ContentContentContentContentContent
                        ContentContentContentContentContentContentContentContentContentContent
                        ContentContentContentContentContent
                        ContentContentContentContentContent
                        ContentContentContentContentContent
                        ContentContentContentContentContentContentContentContentContentContent
                        ContentContentContentContentContent
                        ContentContentContentContentContent
                        ContentContentContentContentContent
                        ContentContentContentContentContentContentContentContentContentContent
                        ContentContentContentContentContent
                        ContentContentContentContentContent
                        ContentContentContentContentContent
                        ContentContentContentContentContentContentContentContentContentContent
                        ContentContentContentContentContent
                        ContentContentContentContentContent
                        ContentContentContentContentContent
                        ContentContentContentContentContentContentContentContentContentContent
                        ContentContentContentContentContent
                        ContentContentContentContentContent
                        ContentContentContentContentContent
                        ContentContentContentContentContentContentContentContentContentContent
                        ContentContentContentContentContent
                        ContentContentContentContentContent
                        ContentContentContentContentContent
                        ContentContentContentContentContentContentContentContentContentContent
                        ContentContentContentContentContent
                        ContentContentContentContentContent
                        ContentContentContentContentContent
                        ContentContentContentContentContentContentContentContentContentContent
                        ContentContentContentContentContent

                    </Content>
                    <Footer>Footer</Footer>
                </Layout>

            </Layout>
        )
    }
}


export default App