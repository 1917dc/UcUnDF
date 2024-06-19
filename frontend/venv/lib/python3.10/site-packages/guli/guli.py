import json
import os
folder = os.path.dirname(os.path.realpath(__file__))+"/data.json"
open(folder,"w").write("{}")

def GuliVariable(variable_name):
  return __global__(variable_name)

class __global__:
  def __init__(self, name):
    self.name = name
  
  def get(self):
    ''' function to get the variable value '''
    _global_list_ = json.loads(open(folder,"r").read())
    if self.name in _global_list_: # check if exists in json
      return _global_list_[self.name] # return variable value
    else:
      return "" # return empty string

  def setValue(self, value):
    ''' function to set the variable value '''
    _global_list_ = json.loads(open(folder,"r").read())
    _global_list_[self.name] = value
    open(folder,"w").write(json.dumps(_global_list_))
    return self
